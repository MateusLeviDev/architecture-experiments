#!/bin/bash

echo "[04] S3 Lambda invoke Function"
awslocal lambda add-permission \
  --function-name ProcessCSV \
  --statement-id s3invoke \
  --action lambda:InvokeFunction \
  --principal s3.amazonaws.com \
  --source-arn arn:aws:s3:::test-bucket
