# AI情感聊天应用 (AI Emotion Chat App)
一个基于React与TypeScript开发的现代化聊天应用，能够分析用户情感并相应地调整回复和界面风格。

项目截图：
![截图1](./accets/p1.png)
![截图2](./accets/p2.png)
![截图3](./accets/p3.png)
![截图4](./accets/p4.png)
![截图5](./accets/p5.png)
![截图6](./accets/p6.png)
![截图7](./accets/p7.png)


## 🌟 特色功能

- **情感分析**：AI能够分析用户消息中的情感倾向，并据此调整回复语气
- **动态界面**：根据聊天氛围自动改变背景颜色，创造沉浸式体验
- **优雅设计**：采用极简主义设计风格，注重用户体验和界面美感
- **深色模式**：支持亮色/深色模式切换，保护用户视力
- **响应式布局**：完美适配各种设备尺寸，从手机到桌面设备
- **历史记录**：保存聊天历史，支持按日期分组查看和管理

## 🔧 技术栈

- **前端框架**：React 19
- **语言**：TypeScript 4.9+
- **UI组件**：基于Tailwind CSS的自定义组件
- **动画效果**：Framer Motion
- **图标**：Lucide React
- **状态管理**：React Hooks
- **其他工具**：Axios (API请求)、UUID (唯一标识符)

## 🚀 快速开始

### 前提条件

确保已安装以下工具：

- Node.js (18.x 或更高版本)
- npm (9.x 或更高版本)

### 安装步骤

1. **克隆仓库**

```bash
git clone https://github.com/iamcbk/ai-emotion-chat.git
cd ai-chat-react
```

2. **安装依赖**

您可以使用包含的批处理脚本自动安装所有必需的依赖：

```bash
start-app.bat
```

或者手动安装：

```bash
npm install
```

3. **启动开发服务器**

```bash
npm start
```

访问 `http://localhost:3000` 即可查看应用。

## 💡 使用指南

### 基本聊天功能

1. 在底部输入框中输入消息并按回车键或点击发送按钮
2. AI会分析你的消息情感并回复，同时调整界面风格
3. 查看历史消息时，可以上下滚动消息列表
4. 使用右下角的箭头按钮快速回到顶部

### 个性化设置

1. 点击右上角的设置图标打开设置面板
2. 使用深色模式开关切换界面主题
3. 需要时可以清空聊天记录

## 🔍 项目结构

```
src/
├── components/           # 组件目录
│   ├── ChatPage.tsx      # 主聊天页面
│   ├── MessageList.tsx   # 消息列表组件
│   └── ui/               # UI组件
│       ├── GlowingInput.tsx     # 发光输入框
│       ├── MessageBubble.tsx    # 消息气泡
│       └── MoodBackground.tsx   # 情绪背景
├── services/             # 服务层
│   └── chatService.ts    # 聊天服务API
├── types/                # 类型定义
│   └── chat.ts           # 聊天相关类型
├── utils/                # 工具函数
│   └── cn.ts             # 类名合并工具
├── App.tsx               # 应用入口组件
└── index.tsx             # 应用入口点
```

## 📱 响应式设计

应用采用移动优先的设计理念：

- 在手机设备上表现良好，支持触摸滑动查看消息
- 在平板和桌面设备上自动调整布局，充分利用更大的屏幕空间
- 支持不同分辨率和屏幕尺寸

## 🎨 界面主题

应用提供两种主题：

- **亮色模式**：清新明亮的界面，适合日间使用
- **深色模式**：深色背景减少眼睛疲劳，适合夜间使用

系统会自动检测用户的系统设置，并应用相应的主题，用户也可以手动切换。

## 🛠️ 自定义开发

### 添加新功能

1. **扩展后端API**：修改`chatService.ts`添加新的API端点
2. **添加新组件**：在`components`目录下创建新组件
3. **扩展类型**：在`types`目录中更新或添加类型定义

### 样式定制

项目使用Tailwind CSS进行样式管理：

- 主题色彩和设计变量在`index.css`中定义
- Tailwind配置在`tailwind.config.js`中进行自定义

## 📄 许可证

MIT © 蔡炳堃

---

如有问题或建议，请随时联系我！！！！

3509214013@qq.com





# AI聊天应用后端

## 项目介绍

这是一个基于Spring Boot和OpenAI API的智能聊天应用后端，提供了丰富的功能，包括多轮对话、情感分析和动态UI调整。系统能够实时分析用户输入的情感和语气，并相应地调整AI回复的风格，同时推荐适合的背景颜色，提供更加个性化和人性化的用户体验。

## 技术栈

- **后端框架**: Spring Boot 3.2.5
- **数据库**: MySQL 8.0+
- **缓存**: Redis (可选)
- **API集成**: OpenAI API 
- **ORM框架**: MyBatis
- **其他工具**: WebFlux, Jackson, Lombok

## 核心功能

1. **智能聊天**
   - 支持多轮对话，AI能够理解上下文
   - 使用大模型生成高质量回复
   - 根据用户设置支持多语言（默认中文）

2. **情感分析**
   - 实时分析AI回复的情感倾向（积极/消极/中性）
   - 识别语气风格（温和/严肃/幽默等）
   - 计算情感强度分值，范围0.000-1.000

3. **动态UI推荐**
   - 根据情感分析结果推荐背景颜色
   - 积极情感: 从浅蓝色(#E6F7FF)到亮绿色(#E6FFE6)
   - 消极情感: 从浅粉色(#FFE6E6)到浅紫色(#F0E6FF)
   - 中性情感: 从浅灰(#F5F5F5)到米色(#F5F0E6)

4. **会话管理**
   - 基于UUID的会话标识，无需用户登录
   - 保存和加载历史消息
   - 支持消息删除功能

## 系统架构

### 分层结构

- **Controller层**: 处理HTTP请求，进行参数验证
- **Service层**: 实现业务逻辑，包括聊天处理和情感分析
- **Mapper层**: 与数据库交互，执行CRUD操作
- **DTO/Entity层**: 数据传输对象和数据库实体类

### 主要组件

1. **ChatController**: 提供消息发送、历史查询和删除API
2. **ChatService**: 处理聊天逻辑，调用OpenAI API
3. **EmotionAnalysisService**: 进行情感分析，生成UI建议
4. **WebClientConfig**: 配置HTTP客户端，处理API调用

## API接口

### 1. 发送消息

- **URL**: `/api/chat/send`

- **方法**: POST

- **请求体**:

  ```json
  {
    "sessionUuid": "550e8400-e29b-41d4-a716-446655440000",
    "content": "你好，今天天气怎么样？",
    "language": "zh"
  }
  ```

- **响应**:

  ```json
  {
    "messageId": 42,
    "content": "今天天气晴朗，阳光明媚，非常适合户外活动！",
    "sentiment": "positive",
    "tone": "cheerful",
    "emotionScore": 0.85,
    "backgroundColor": "#E6FFE6",
    "timestamp": "2024-04-25T11:30:15.123Z"
  }
  ```

### 2. 获取历史消息

- **URL**: `/api/chat/history/{sessionUuid}`
- **方法**: GET
- **响应**: 消息列表

### 3. 删除消息

- **URL**: `/api/chat/message/{messageId}`
- **方法**: DELETE
- **响应**: 布尔值表示操作结果

## 数据库设计

### 表结构

1. **chat_session表**
   - id: 主键
   - session_uuid: 会话UUID
   - created_at: 创建时间
   - last_active: 最后活动时间
   - ip_address: 用户IP地址

2. **chat_message表**
   - id: 主键
   - session_id: 会话ID (外键)
   - sender: 发送者 ("user"或"ai")
   - content: 消息内容
   - sentiment: 情感分类
   - tone: 语气风格
   - emotion_score: 情感强度
   - language: 语言
   - is_deleted: 是否删除标记
   - created_at: 创建时间

## 快速开始

### 环境要求

- Java 21
- Maven 3.6+
- MySQL 8.0+
- Redis (可选)
- OpenAI API密钥

### 配置

1. 克隆项目
2. 修改`application.yml`中的数据库配置和API密钥
3. 创建数据库和表

```sql
CREATE DATABASE ai_chat;
USE ai_chat;

CREATE TABLE chat_session (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    session_uuid VARCHAR(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_active TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ip_address VARCHAR(45)
);

CREATE TABLE chat_message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    session_id BIGINT NOT NULL,
    sender VARCHAR(10) NOT NULL,
    content TEXT NOT NULL,
    sentiment VARCHAR(20),
    tone VARCHAR(20),
    emotion_score DECIMAL(5,3),
    language VARCHAR(10) DEFAULT 'zh',
    is_deleted TINYINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES chat_session(id)
);
```

### 运行

```bash
# 使用Maven运行
mvn spring-boot:run

# 或构建后运行
mvn clean package
java -jar target/ai-chat-backend-0.0.1-SNAPSHOT.jar
```

## 高级配置

### 自定义OpenAI API设置

在`application.yml`中定制：

```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY:your-api-key}
      base-url: ${OPENAI_BASE_URL:your-api-url}
      chat:
        options:
          model: your-ai-model
          temperature: 0.7
```

### 调整日志级别

```yaml
logging:
  level:
    ai.aichatbackend: debug
    org.springframework.ai: info
    org.springframework.web: debug
```

## 部署

### Docker部署

1. 构建Docker镜像：

```bash
docker build -t ai-chat-backend .
```

2. 运行容器：

```bash
docker run -d -p 7777:7777 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-host:3306/ai_chat \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=password \
  -e OPENAI_API_KEY=your-api-key \
  ai-chat-backend
```

### 生产环境注意事项

- 启用HTTPS保护API通信
- 配置适当的CORS策略
- 设置速率限制防止API滥用
- 考虑实施用户认证和授权

## 项目拓展

### 可能的功能增强

1. 添加用户认证系统
2. 实现更复杂的情感分析模型
3. 增加个性化聊天风格设置
4. 集成多种LLM模型供选择
5. 支持语音输入和语音合成
6. 增加图像生成和理解能力

## 故障排除

### 常见问题

1. **OpenAI API连接问题**: 检查API密钥和网络连接
2. **406错误**: 确保Content-Type和Accept头设置正确
3. **数据库连接失败**: 验证数据库配置和权限
4. **内存不足**: 调整JVM内存设置或减小响应缓冲区大小

## 项目团队

- 开发人员：蔡炳堃
- 设计人员：蔡炳堃
- 项目负责人：蔡炳堃

## 许可证

[MIT License](LICENSE)
