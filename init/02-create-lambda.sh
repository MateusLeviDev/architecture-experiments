#!/bin/bash

echo "[02] Creating Function"
awslocal lambda create-function \
  --function-name ProcessCSV \
  --runtime go1.x \
  --handler main \
  --role arn:aws:iam::000000000000:role/lambda-role \
  --timeout 15 \
  --zip-file fileb:///etc/localstack/init/ready.d/function.zip
