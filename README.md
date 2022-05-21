# Tight-url

It is a simple URL shorter project. You can do URL shorting simply, and even count the number of hits for that URL.

# Webpage

- https://tight.ga

# API
- Swagger https://tight.ga/swagger-ui/

POST /api/shorten-urls
```json
{
    "url": "google.co.kr"
}
```

Response
```json
{
  "originUrl": "string",
  "tag": "string",
  "userId": 0
}
```

GET /{tag}

HTTP 301 (moved permanently)
