<p align="center">
  <h1 align="center">📨 Openfire Kafka Listener</h1>
  <p align="center">
    <strong>Consumes Openfire chat logs from a Kafka topic and stores them in MongoDB for retention.</strong>
  </p>
  <p align="center">
    <img src="https://img.shields.io/badge/Java-8-ed8b00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
    <img src="https://img.shields.io/badge/Spring_Boot-2.3-6db33f?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring_Boot">
    <img src="https://img.shields.io/badge/Apache_Kafka-consumer-231f20?style=for-the-badge&logo=apachekafka&logoColor=white" alt="Apache_Kafka">
    <img src="https://img.shields.io/badge/MongoDB-storage-47a248?style=for-the-badge&logo=mongodb&logoColor=white" alt="MongoDB">
    <img src="https://img.shields.io/github/last-commit/pratiks360/Pratik_Openfire_Kafka_Listener?style=for-the-badge&label=Last+Commit" alt="Last Commit">
  </p>
</p>

---

## ✨ What Is This?

An Openfire (XMPP) server publishes every message packet to Kafka. This service subscribes to the `chat_logs` topic and writes each message — sender, recipient, body, timestamp and device ID — into MongoDB so chats can be retained and audited.

## 🧩 How It Works

```
┌──────────┐  publish  ┌───────────────────┐  @KafkaListener  ┌────────────┐  insert  ┌─────────┐
│ Openfire │ ────────► │ Kafka: chat_logs  │ ───────────────► │  Listner   │ ───────► │ MongoDB │
└──────────┘           └───────────────────┘                  └────────────┘          └─────────┘
```

1. **Consume** — `Listner` receives the JSON packet on topic `chat_logs` (group `groupId`).
2. **Map** — Jackson deserialises it into `PacketMessage`.
3. **Store** — `MessageRepo.insertChatRecords` writes the row.

## 🚀 Quick Start

**Prerequisites:** JDK 8+, Gradle, a Kafka broker and MongoDB.

```bash
git clone https://github.com/pratiks360/Pratik_Openfire_Kafka_Listener.git
cd Pratik_Openfire_Kafka_Listener
./gradlew bootRun
```

Broker and database settings go in `src/main/resources/application.yaml`; `docs/` has the MongoDB bootstrap commands.

## 🔒 Security Notes

- `docs/` contains example database-user commands with a sample password — create your own credentials and keep them out of git.

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Framework** | Spring Boot 2.3 |
| **Messaging** | Spring for Apache Kafka |
| **Storage** | MongoDB Java driver 3.12 |

---

<p align="center">
  <sub>Built with ❤️ for chat retention.</sub>
</p>
