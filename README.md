# Tight-url

심플한 URL 쇼터 프로젝트 입니다. 간단하게 URL 쇼팅을 할 수 있으며, 해당 URL에 대한 hit 수 카운트까지 가능합니다.

# Webpage

- https://tight.ga

# API
- 스웨거 https://tight.ga/swagger-ui/

### URL 등록

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

### 조회

GET /{tag}

HTTP 301 (moved permanently)
