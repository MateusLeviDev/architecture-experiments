#!/bin/bash

echo "[03] Put trigger S3 → Lambda"
awslocal lambda wait function-active-v2 --function-name ProcessCSV
awslocal s3api put-bucket-notification-configuration --bucket test-bucket --notification-configuration '{
  "LambdaFunctionConfigurations": [
    {
      "LambdaFunctionArn": "arn:aws:lambda:us-east-1:000000000000:function:ProcessCSV",
      "Events": ["s3:ObjectCreated:*"]
    }
  ]
}'
