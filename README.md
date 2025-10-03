📌 Project Overview

Smart File Organizer is a Java Spring Boot backend application that helps users automatically organize files in their system. It scans a directory and classifies files into categories like documents, images, videos, music, and others.

This project demonstrates backend development skills including Spring Boot, REST API design, MySQL integration, and scalable architecture.

🚀 Features

✅ Automatic File Organization – scans a directory and moves files into categorized folders.

✅ REST APIs – expose endpoints to trigger file scans and get organization status.

✅ MySQL Integration – stores metadata and file operation history.

✅ Maven Build Automation – for dependency management and packaging.

✅ Scalable Architecture – ready for future enhancements like progress bar, undo, and cloud sync.

🛠️ Tech Stack

Language: Java

Framework: Spring Boot

Database: MySQL

Build Tool: Maven

Version Control: Git & GitHub

📂 Project Structure
smart-file-organizer-backend/
│── src/main/java/com/example/organizer/
│   │── SmartFileOrganizerBackendApplication.java   # Main Spring Boot entry point
│   │── controller/                                # REST controllers
│   │── service/                                   # Business logic
│   │── repository/                                # JPA Repositories
│   │── model/                                     # Entity classes
│
│── src/main/resources/
│   │── application.properties                     # DB config, server settings
│
│── pom.xml                                        # Maven dependencies

⚡ Getting Started
1️⃣ Clone the Repository
git clone https://github.com/your-username/smart-file-organizer-backend.git
cd smart-file-organizer-backend

2️⃣ Configure MySQL Database

Create a new database (e.g., smart_file_db)

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/smart_file_db
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the Application

Using Maven:

mvn spring-boot:run


Or build a JAR and run:

mvn clean package
java -jar target/smart-file-organizer-backend-0.0.1-SNAPSHOT.jar

📡 API Endpoints (Sample)
Method	Endpoint	Description
POST	/api/files/scan	Trigger file scan & organization
GET	/api/files/logs	Get history of organized files
🌱 Future Enhancements

📊 Progress bar for file organization

🔄 Undo last operation

☁️ Cloud storage integration (Google Drive/Dropbox)

🎨 Frontend dashboard (React + Tailwind)

🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you’d like to change.

📜 License

This project is licensed under the MIT License.
