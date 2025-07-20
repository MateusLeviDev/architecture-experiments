- baixei aws-cli e setei as configs default
- da pra usar pelo container o awslocal
- O awslocal é um wrapper do AWS CLI que já configura automaticamente o endpoint para http://localhost:4566. sem precisar definir manualmente AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY ou AWS_DEFAULT_REGION...

---

<img width="1019" height="604" alt="Screenshot from 2025-07-18 15-11-36" src="https://github.com/user-attachments/assets/2618a852-dd5c-45ab-93a6-558550b97d21" />
<img width="722" height="149" alt="Screenshot from 2025-07-18 15-45-09" src="https://github.com/user-attachments/assets/f0b1bc9f-6fad-4c81-8af8-f8e1535eb46b" />
<img width="1142" height="71" alt="Screenshot from 2025-07-18 15-45-27" src="https://github.com/user-attachments/assets/a09f9f61-c4b3-4f61-ac93-1908cee45468" />
<img width="1033" height="937" alt="Screenshot from 2025-07-18 15-45-52" src="https://github.com/user-attachments/assets/3c720878-8d42-4481-8877-c9fc93604014" />


## lambda

-  GOOS=linux GOARCH=amd64 CGO_ENABLED=0 go build -o bootstrap cmd/main.go

- zip bootstrap.zip boostrap

- aws --endpoint-url=http://localhost:4566 s3 mb s3://local-bucket

- aws --endpoint-url=http://localhost:4566 s3 cp bootstrap.zip s3://local-bucket/bootstrap.zip

-  aws --endpoint-url=http://localhost:4566 lambda list-functions

```

aws --endpoint-url=http://localhost:4566 lambda create-function \
  --function-name HelloWorld \
  --runtime provided.al2 \
  --handler bootstrap \
  --role arn:aws:iam::000000000000:role/lambda-role \
  --code S3Bucket=local-bucket,S3Key=bootstrap.zip \
  --region us-east-1

  container
  awslocal lambda invoke \                                                                
  --function-name HelloWo> rld \
  output.json


  host: 
  aws --endpoint-url=http://localhost:4566 lambda invoke \
  --function-name HelloWorld \
  output.json
```

- testar depois 
- GOOS=linux GOARCH=amd64 go build -o bootstrap ./cmd/main.go

```

aws lambda create-function \
  --function-name HelloWorld \
  --runtime provided.al2 \
  --handler bootstrap \
  --zip-file fileb://bootstrap.zip \
  --role arn:aws:iam::000000000000:role/lambda-role \
  --endpoint-url http://localhost:4566
```
