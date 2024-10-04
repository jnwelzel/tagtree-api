# Tagtree API
This is the backend REST API used by [Tagtree](https://github.com/jnwelzel/tagtree/).

The project is written in Java using the Spring Boot framework.

Request bodies should always be provided as `Application/JSON`.

Publicly available endpoints:
 - POST `/api/v1/auth/login`
   - `email`: string
   - `password`: string
 - POST `/api/v1/auth/register`
     - `email`: string
     - `password`: string
     - `userName`: string
     - `firstName`: string
     - `lastName`: string

JWT protected resources:
 - GET `/api/v1/users`
 - GET `/api/v1/users/{id}`
 - GET `/api/v1/users/{id}/tags`
 - GET `/api/v1/users/{id}/tags/{id}`

## Set up

### Certificates
To sign and verify the JWTs issued by the API we need to create two keys using the following steps:
1. Create a `certs` folder under `/src/main/resources`
2. Create the RSA key pair: `openssl genrsa -out keypair.pem 2048`
3. Extract the public key: `openssl rsa -in keypair.pem -pubout -out public.pem`
4. Create the private key using PKCS#8 format: `openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem`

If no errors, you can safely delete the `keypair.pem` file now since we won't be needing it anymore.

