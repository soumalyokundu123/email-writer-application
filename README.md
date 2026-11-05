# ✉️ AI Email Writer

An AI-powered Email Writer application that helps users generate professional, well-structured email content using Google's Gemini AI.

## 🚀 Overview

AI Email Writer simplifies email composition by generating contextual email responses based on user-provided prompts and instructions.

The application uses a React frontend and a Java Spring Boot backend, integrating Google's Gemini API to generate email content.

## ✨ Features

- **AI-Powered Email Generation:** Generate email content using Gemini AI.
- **REST API Integration:** Communicate between frontend and backend using REST APIs.
- **Spring Boot Backend:** Structured backend architecture using Java.
- **Interactive Frontend:** User-friendly interface built with React.
- **Secure API Configuration:** Store API credentials using environment variables.

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring WebFlux
- WebClient
- Maven
- REST APIs

### Frontend
- React.js
- JavaScript
- HTML5
- CSS3
- Vite

### AI Integration
- Google Gemini API

## 📁 Project Structure

```text
email-writer-application/
│
├── email-writer-sb/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/email/writer/
│   │   │   │   ├── EmailGeneratorController.java
│   │   │   │   ├── EmailGeneratorService.java
│   │   │   │   ├── EmailRequest.java
│   │   │   │   ├── WebClientConfig.java
│   │   │   │   └── EmailWriterSbApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── mvnw
│
├── frontend/
│   └── email_writer/
│       ├── src/
│       ├── public/
│       ├── package.json
│       └── vite.config.js
│
├── .gitignore
└── README.md
```

## ⚙️ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/email-writer-application.git
```

```bash
cd email-writer-application
```

### 2. Backend Setup (Spring Boot)

Navigate to the backend directory:

```bash
cd email-writer-sb
```

Configure your `application.properties`:

```properties
spring.application.name=email-writer-sb
gemini.api.key=${GEMINI_KEY}
```

Set your Gemini API key as an environment variable.

**Windows PowerShell:**

```powershell
$env:GEMINI_KEY="YOUR_GEMINI_API_KEY"
```

Run the Spring Boot application:

```powershell
.\mvnw.cmd spring-boot:run
```

### 3. Frontend Setup (React)

Open another terminal and navigate to:

```bash
cd frontend/email_writer
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Open the local URL displayed by Vite in your browser.

## 🔐 Environment Variables

The application requires the following environment variable:

| Variable | Description |
|---|---|
| `GEMINI_KEY` | Google Gemini API key |

**Security Note:** Never commit API keys, passwords, or other sensitive credentials to GitHub.

## 🔄 Application Workflow

1. The user interacts with the React frontend.
2. The frontend communicates with the Spring Boot backend.
3. The backend processes the email generation request.
4. Spring WebClient communicates with the Gemini API.
5. Gemini generates the email content.
6. The generated response is returned to the frontend.

## 🎯 Project Objective

The objective of this project is to explore the integration of Generative AI with modern Java backend development while building a practical application using Spring Boot, React, and REST APIs.

## 👨‍💻 Author

**Soumalyo Kundu**

B.Tech – Computer Science & Engineering

GitHub: [Your GitHub Profile](https://github.com/YOUR_USERNAME)

---

⭐ If you find this project interesting, consider giving it a star!
