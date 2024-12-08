# Order Management System

This project consists of two microservices: **OrderService** and **InventoryService**, which are part of an **Order Management System**. The services communicate using Kafka for handling order creation and inventory updates.

## Requirements

Before setting up the project, ensure you have the following installed:

- Java 11 or higher
- Maven or Gradle
- Kafka
- H2 Database (for local testing)

## Set up Kafka

1. **Download and Install Kafka**:
   - Download Kafka from the official site: https://kafka.apache.org/downloads
   - Extract the archive to your desired location.
   
2. **Start Zookeeper** (Kafka requires Zookeeper to run):
   - In the Kafka directory, run:
     ```bash
     bin/zookeeper-server-start.sh config/zookeeper.properties
     ```
   - On Windows:
     ```bash
     .\bin\windows\zookeeper-server-start.bat config\zookeeper.properties
     ```

3. **Start Kafka Broker**:
   - In the Kafka directory, run:
     ```bash
     bin/kafka-server-start.sh config/server.properties
     ```
   - On Windows:
     ```bash
     .\bin\windows\kafka-server-start.bat config\server.properties
     ```

4. **Create Kafka Topics**:
   - Create the `order.created` and `order.processed` topics that will be used for communication between services:
     ```bash
     bin/kafka-topics.sh --create --topic order.created --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
     bin/kafka-topics.sh --create --topic order.processed --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
     ```

   - On Windows:
     ```bash
     .\bin\windows\kafka-topics.bat --create --topic order.created --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
     .\bin\windows\kafka-topics.bat --create --topic order.processed --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
     ```

## Setting up the Project

### Clone the Repository

```bash
git clone <repository_url>
cd <project_directory>

