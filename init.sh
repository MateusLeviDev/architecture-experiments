#!/bin/bash

set -e

echo "---------- Creating bucket S3..."
awslocal s3 mb s3://meu-bucket-csv

echo "---------- Creating Lambda (Go)..."
# Espera que function.zip com o binário main já exista na raiz
# Se preferir, você pode incluir o zip dentro do script também, mas geralmente faz antes.

awslocal lambda create-function \
  --function-name ProcessCSV \
  --runtime go1.x \
  --handler main \
  --role arn:aws:iam::000000000000:role/lambda-role \
  --zip-file fileb://function.zip

echo "---------- Configuring S3 event trigger to Lambda..."
awslocal s3api put-bucket-notification-configuration --bucket meu-bucket-csv \
  --notification-configuration '{
    "LambdaFunctionConfigurations": [
      {
        "LambdaFunctionArn": "arn:aws:lambda:us-east-1:000000000000:function:ProcessCSV",
        "Events": ["s3:ObjectCreated:*"]
      }
    ]
  }'

echo "---------- Adding permission for S3 to invoke Lambda..."
awslocal lambda add-permission \
  --function-name ProcessCSV \
  --statement-id s3invoke \
  --action "lambda:InvokeFunction" \
  --principal s3.amazonaws.com \
  --source-arn arn:aws:s3:::meu-bucket-csv

echo "Setup complete!"