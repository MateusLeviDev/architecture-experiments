#!/bin/bash

export PUBSUB_EMULATOR_HOST=localhost:8085
export PROJECT_ID=emulator
declare -a TOPICS=("employee-batch-regulatory-validated")

waitForUrlHealthCheck(){
  end=30
  for i in $(eval echo "{1..$end}"); do
    curl --fail --silent "$2" | grep -q 'Ok' && break;
    echo "[${i}/${end}] waiting $1"
    sleep 5
  done
}

gcloud beta emulators pubsub start --host-port=0.0.0.0:8085 &
waitForUrlHealthCheck 'pub-sub' "http://${PUBSUB_EMULATOR_HOST}"

for topic in "${TOPICS[@]}"
   do
     sub="${topic}-sub"
     dlqSub="${topic}-dlq-sub"
     dlqTpc="${topic}-dlq"
     curl -X PUT "http://${PUBSUB_EMULATOR_HOST}/v1/projects/${PROJECT_ID}/topics/${dlqTpc}"
     curl -X PUT "http://${PUBSUB_EMULATOR_HOST}/v1/projects/${PROJECT_ID}/topics/${topic}"
     curl -X PUT "http://${PUBSUB_EMULATOR_HOST}/v1/projects/${PROJECT_ID}/subscriptions/${dlqSub}" -H "Content-Type: application/json" -d "{\"topic\": \"projects/${PROJECT_ID}/topics/${dlqTpc}\",  \"ackDeadlineSeconds\": 20}"
     curl -X PUT "http://${PUBSUB_EMULATOR_HOST}/v1/projects/${PROJECT_ID}/subscriptions/${sub}" -H "Content-Type: application/json" -d "{\"topic\": \"projects/${PROJECT_ID}/topics/${topic}\", \"ackDeadlineSeconds\": 20, \"deadLetterPolicy\": { \"deadLetterTopic\": \"projects/${PROJECT_ID}/topics/${dlqTpc}\", \"maxDeliveryAttempts\": 5 }}"
   done
tail -f /dev/null
