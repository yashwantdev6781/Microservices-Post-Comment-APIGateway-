# Microservices Project: Posts, Comments, and API Gateway

This project demonstrates a microservices-based architecture for managing posts, comments, and an API gateway. It uses modern tools and frameworks to build scalable, resilient, and well-structured services.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Services](#running-the-services)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This project consists of three microservices:

1. **Post Service**: Manages blog posts.
2. **Comment Service**: Handles comments associated with posts.
3. **API Gateway**: Acts as a single entry point for external requests, routing them to the appropriate microservices.

The project emphasizes modularity, scalability, and reliability.

## Features

- CRUD operations for posts and comments
- Centralized API gateway for request routing
- Communication between services using REST or messaging protocols
- Service discovery and fault tolerance

## Architecture

```
+-------------+    +-------------------+
|  API Client | -> |    API Gateway    |
+-------------+    +-------------------+
                         |
       +-----------------+-----------------+
       |                                   |
+---------------+                  +----------------+
|   Post Service |                  | Comment Service |
+---------------+                  +----------------+
```

- **API Gateway**: Handles all incoming requests and forwards them to the respective services.
- **Post Service**: Manages posts and their metadata.
- **Comment Service**: Manages comments linked to specific posts.
