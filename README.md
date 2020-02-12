# Health-Net Backend Users Service

Users CRUD service

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

```
node.js
```

### Installing

Install npm project dependencies
```
npm install
```

## Deployment

1. Set up MySQL database
1. Run setup-database.sh script located in src/main/resources/scripts
2. Set environment variables inline and run service

## Details
### Environment Variables
| Name          | Description               | Default value |
|---------------|---------------------------|---------------|
| SERVER_PORT   | The server port           |  3000         |
| DB_HOST       | The database host         | localhost     |
| DB_PORT       | The database port         | 3306          |
| DB_USER       | The database user         | root          |
| DB_PASSWORD   | The database password     | N.A.          |
| CLIENT_ID     | The Auth0 client's id     | N.A.          |
| CLIENT_SECRET | The Auth0 client's secret | N.A.          |
