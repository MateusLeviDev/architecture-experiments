package main

import (
	"context"
	"fmt"

	"github.com/aws/aws-lambda-go/lambda"
)

func handler(ctx context.Context, event map[string]interface{}) (map[string]interface{}, error) {
	fmt.Println("Lambda triggered by S3 event:")
	fmt.Printf("%+v\n", event)

	return map[string]interface{}{
		"statusCode": 200,
		"body":       "Success",
	}, nil
}

func main() {
	lambda.Start(handler)
}
