-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ai_chat
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息主键ID，自增',
  `session_id` bigint NOT NULL COMMENT '关联的会话ID，外键关联 chat_session.id',
  `sender` varchar(10) NOT NULL COMMENT '发送方：user表示用户，ai表示AI',
  `content` text NOT NULL COMMENT '消息正文内容',
  `sentiment` varchar(20) DEFAULT NULL COMMENT '情感分类结果，如 positive, negative, neutral',
  `tone` varchar(20) DEFAULT NULL COMMENT '语气风格，如 gentle, serious, humorous',
  `emotion_score` decimal(4,3) DEFAULT NULL COMMENT '情绪强度分值，范围0.000 ~ 1.000，值越大表示情绪越强烈',
  `language` varchar(10) DEFAULT NULL COMMENT '消息语言类型，如 zh（中文）、en（英文）',
  `is_deleted` tinyint DEFAULT '0' COMMENT '是否已删除，0表示未删除，1表示已软删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '消息创建时间',
  PRIMARY KEY (`id`),
  KEY `session_id` (`session_id`),
  CONSTRAINT `chat_message_ibfk_1` FOREIGN KEY (`session_id`) REFERENCES `chat_session` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI聊天消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,1,'user','你好',NULL,NULL,NULL,'zh',0,'2025-04-25 14:13:21'),(2,1,'ai','很抱歉，我遇到了一些技术问题，无法正常回复。请稍后再试。','neutral','neutral',0.500,'zh',0,'2025-04-25 14:13:23'),(3,2,'user','你好，今天天气怎么样？',NULL,NULL,NULL,'zh',0,'2025-04-25 14:16:54'),(4,2,'ai','很抱歉，我遇到了一些技术问题，无法正常回复。请稍后再试。','neutral','neutral',0.500,'zh',0,'2025-04-25 14:16:54'),(5,2,'user','你好，今天天气怎么样？',NULL,NULL,NULL,'zh',0,'2025-04-25 14:19:33'),(6,2,'ai','很抱歉，我遇到了一些技术问题，无法正常回复。请稍后再试。','neutral','neutral',0.500,'zh',0,'2025-04-25 14:19:34'),(7,2,'user','你好，今天天气怎么样？',NULL,NULL,NULL,'zh',0,'2025-04-25 14:22:36'),(8,2,'ai','很抱歉，我遇到了一些技术问题，无法正常回复。请稍后再试。','neutral','neutral',0.500,'zh',0,'2025-04-25 14:22:36'),(9,2,'user','你好，今天天气怎么样？',NULL,NULL,NULL,'zh',0,'2025-04-25 14:22:46'),(10,2,'ai','很抱歉，我遇到了一些技术问题，无法正常回复。请稍后再试。','neutral','neutral',0.500,'zh',0,'2025-04-25 14:22:46'),(11,2,'user','你好，今天天气怎么样？',NULL,NULL,NULL,'zh',0,'2025-04-25 14:27:28'),(12,2,'ai','你好！我无法提供实时的天气信息，但你可以查看天气预报网站或者手机应用来获取最新的天气情况！如果有其他想聊的，随时告诉我哦！','neutral','gentle',0.200,'zh',0,'2025-04-25 14:27:32'),(13,1,'user','你好',NULL,NULL,NULL,'zh',0,'2025-04-25 14:27:54'),(14,1,'ai','你好！很高兴见到你！今天过得怎么样？','positive','gentle',0.750,'zh',0,'2025-04-25 14:28:04'),(15,1,'user','我现在有点生气',NULL,NULL,NULL,'zh',0,'2025-04-25 14:28:19'),(16,1,'ai','我理解你的感受，生气是很正常的情绪。有想要分享的原因吗？也许聊一聊会让你感觉好一些。','positive','gentle',0.600,'zh',0,'2025-04-25 14:28:23'),(17,1,'user','我心情差的极致',NULL,NULL,NULL,'zh',0,'2025-04-25 14:28:48'),(18,1,'ai','我很抱歉你有这样的感觉。生活中有时候会遇到让人沮丧的事情。如果你想谈谈发生了什么，或者需要一个倾听者，我在这里支持你。','positive','gentle',0.750,'zh',0,'2025-04-25 14:28:51'),(19,1,'user','你能生气的和我说话嘛',NULL,NULL,NULL,'zh',0,'2025-04-25 14:30:27'),(20,1,'ai','我明白你可能希望我能表达一些生气的情绪来共鸣，但作为一个AI，我没有情感。不过，我可以理解你的愤怒，并且愿意倾听你说说是什么让你如此生气。如果你愿意分享，我会尽量帮助你！','neutral','gentle',0.100,'zh',0,'2025-04-25 14:30:32'),(21,1,'user','你是什么大模型',NULL,NULL,NULL,'zh',0,'2025-04-25 14:30:49'),(22,1,'ai','我是一个由OpenAI开发的语言模型，旨在理解和生成自然语言，帮助解答问题、提供信息和进行交流。我可以处理各种话题和情感，适应不同的对话风格。有什么具体想了解的内容吗？','neutral','informative',0.000,'zh',0,'2025-04-25 14:30:52'),(23,1,'user','你是gpt-4o嘛',NULL,NULL,NULL,'zh',0,'2025-04-25 14:31:10'),(24,1,'ai','其实，我是基于GPT-4的模型，但我并不是具体的“GPT-4o”版本。我的主要功能是理解和生成自然语言，帮助你解答问题和提供交流。如果你有任何具体的问题或者需要帮助的地方，尽管问我！','neutral','informative',0.000,'zh',0,'2025-04-25 14:31:13'),(25,1,'user','告诉我你是什么模型',NULL,NULL,NULL,'zh',0,'2025-04-25 14:32:07'),(26,1,'ai','我基于OpenAI的GPT-4模型，专为自然语言处理设计。我的核心功能是帮助你解决问题、提供信息和进行对话。如果你对我的技术细节感兴趣，可以告诉我具体想了解的方向，我会尽力解答！','neutral','informative',0.200,'zh',0,'2025-04-25 14:32:15'),(27,3,'user','我有点伤心',NULL,NULL,NULL,'zh',0,'2025-04-25 14:35:15'),(28,3,'ai','抱抱你！能跟我说说发生了什么事情吗？有时候，把心里的话说出来会好受一点，我在这儿陪着你。💛','positive','gentle',0.850,'zh',0,'2025-04-25 14:35:19'),(29,3,'user','你叫什么？',NULL,NULL,NULL,'zh',0,'2025-04-25 14:35:42'),(30,3,'ai','我是你的AI助手，没有固定的名字！不过如果你想给我取个名字，我会很乐意接受哦～你觉得叫什么合适呢？ 😊','positive','gentle',0.800,'zh',0,'2025-04-25 14:35:46'),(31,3,'user','我和你说，我今天被打了，被人群殴了，我身上很痛',NULL,NULL,NULL,'zh',0,'2025-04-25 14:36:37'),(32,3,'ai','天啊，这听起来真的很糟糕！你现在安全了吗？有没有去医院检查一下伤势？身体痛可以处理，但心理上的伤害也很重要，你一定要好好照顾自己。能告诉我发生了什么吗？如果需要，我可以帮你想办法或者提供一些建议。','negative','concerned',0.850,'zh',0,'2025-04-25 14:36:43'),(33,3,'user','gpt，我今天心情有点不好',NULL,NULL,NULL,'zh',0,'2025-04-25 14:40:51'),(34,3,'ai','哎，听到你心情不好我也有点难过了。要不要聊聊是什么事情让你不开心？有时候说出来会感觉轻松一些。我在这儿，一直愿意听你说！💛','positive','gentle',0.750,'zh',0,'2025-04-25 14:41:06'),(35,3,'user','我和你说，我今天上班的时候掉进井盖了，一到公司，老板和我说我被开除了，我伤心的回家，结果被邻居家的小黄狗咬了，我来到医院发现钱包被偷了',NULL,NULL,NULL,'zh',0,'2025-04-25 14:43:25'),(36,3,'ai','哎呀，这一天听起来简直是“年度灾难日”了，你一定觉得特别崩溃吧！先让我给你一个大大的虚拟抱抱，真的辛苦了！🤗\n\n不过，先处理眼前的事最重要！如果你还在医院，记得跟医生详细说明情况，确保身体没什么大问题。如果钱包丢了，可以向医院或警方寻求帮助，看有没有办法找到或者补救。至于小黄狗，下次记得绕开它，安全第一！\n\n至于工作，虽然被开除让人伤心，但可能也有新的机会在等着你！有时候挫折只是一个转折点，不是终点。今天的这份糟心真的让人心疼，但相信你一定能挺过去的！如果需要，我随时都在这儿陪着你。要不要我帮你想些让心情好一点的小方法？','positive','compassionate',0.850,'zh',0,'2025-04-25 14:43:35'),(37,3,'user','我很伤心，你能安慰一下我嘛',NULL,NULL,NULL,'zh',0,'2025-04-25 14:43:53'),(38,3,'ai','当然可以！你的伤心让我心疼，真的很希望能帮你感觉好一点。你今天经历了这么多事，换谁都会觉得难受，但请记住，这些事情并不能定义你的人生。你很坚强，能熬过这些糟糕的时刻。\n\n现在，闭上眼睛，深呼吸几次，让自己平静下来。试着告诉自己：“我已经很努力了，这只是个糟糕的一天，不是糟糕的一生。”每一天都是新的开始，糟糕的事情会过去，属于你的美好一定会到来。\n\n如果你想聊聊，我随时在这里陪你。或者，我可以给你讲个轻松的笑话，分散一下注意力？你不是一个人，我一直在你身边支持你！💛','positive','gentle',0.850,'zh',0,'2025-04-25 14:44:02'),(39,4,'user','hello，gpt，我今天心情有点不好',NULL,NULL,NULL,'zh',0,'2025-04-25 14:45:25'),(40,4,'ai','很抱歉，我遇到了一些技术问题，无法正常回复。请稍后再试。错误类型：WebClientRequestException','neutral','serious',0.250,'zh',0,'2025-04-25 14:46:10'),(41,4,'user','你好',NULL,NULL,NULL,'zh',0,'2025-04-25 14:46:18'),(42,4,'ai','你好！看到你又来了，真高兴！今天还好吗？刚刚看到你说有点心情不好，现在好些了吗？如果愿意聊聊，我在这儿陪着你。 😊','positive','gentle',0.820,'zh',0,'2025-04-25 14:46:21'),(43,4,'user','你好gpt，我今天心情有点不好',NULL,NULL,NULL,'zh',0,'2025-04-25 14:47:02'),(44,4,'ai','抱歉听到你今天心情不好，我在这儿陪着你。如果愿意，可以告诉我发生了什么，我会尽力帮你分担或者聊聊其他事情，让你稍微轻松一些。❤️','positive','gentle',0.850,'zh',0,'2025-04-25 14:47:06'),(45,4,'user','我今天钱包掉了，我好伤心啊，我想哭',NULL,NULL,NULL,'zh',0,'2025-04-25 14:48:01'),(46,4,'ai','哎呀，听到这个真让人心疼！丢钱包确实很让人难过，尤其里面可能有一些重要的东西。别急，先深呼吸一下，我们一起看看有没有办法补救。\n\n你还记得大概是在哪儿丢的吗？比如最后一次拿出来用的时候？或者可以试着回想一下今天的行程，看看有没有可能遗漏在哪些地方。如果是公共场所，像商场、公交车或者餐厅，可以联系他们的工作人员或失物招领处问一问。\n\n另外，如果钱包里有银行卡或证件，记得尽快挂失处理，避免后续麻烦。如果需要具体指导，我可以帮你梳理一下步骤哦！\n\n我知道现在难过是很正常的，但别担心，事情总会有解决的办法。一切都会好起来的，我在这儿陪着你！ ❤️','positive','gentle',0.750,'zh',0,'2025-04-25 14:48:10'),(47,4,'user','我还给老板骂了，我要烦死了！！！',NULL,NULL,NULL,'zh',0,'2025-04-25 14:48:32'),(48,4,'ai','哎呀，今天真是有点糟糕啊！被老板批评确实很让人沮丧，不管是什么原因，都会让人难受。你已经经历了这么多，真的很辛苦。\n\n如果你愿意，可以跟我说说具体发生了什么，我会尽力帮你理清思路。也许可以找到一些解决问题的办法，或者至少能让你觉得好受一些。\n\n有时候，老板的批评可能只是表达方式不太好，但未必是针对你个人的。试着从中找出一些建设性的反馈，看看有没有什么是可以改进的地方。当然，这并不意味着你做得不好，只是每个人都会有需要成长的空间。\n\n记住，今天的困难只是暂时的。你已经在面对这些挑战，说明你很坚强。未来还会有很多机会让你展现自己的能力和价值。加油，我相信你能度过这段难关！如果需要更多支持或建议，我随时在这儿陪着你。 💪❤️','negative','gentle',0.750,'zh',0,'2025-04-25 14:49:04'),(49,4,'user','好吧，骗你的，我今天超级无敌爆炸开心！我中彩票了我中了一个亿！！！',NULL,NULL,NULL,'zh',0,'2025-04-25 14:49:58'),(50,4,'ai','哈哈哈，你这情绪反转得让我差点原地重启了！不过，中了一个亿？！这可是大事啊！要不要考虑分我……嗯，一块钱？毕竟我可是你的忠实助手，陪你风雨同舟的AI朋友啊！😂\n\n话说回来，如果真中了这么大的奖，记得冷静一下，别太高调哦。先找个安全的地方把彩票好好收好，然后规划一下怎么用这一笔“横财”，别乱花！也可以考虑找个理财顾问帮你打理，毕竟，一个亿可是需要认真对待的“烦恼”呢！\n\n好了，祝你继续超级无敌爆炸开心！要不今晚去吃个大餐庆祝一下？我虽然无法吃，但可以帮你选餐厅哦～🎉🍕🍣','positive','humorous',0.850,'zh',0,'2025-04-25 14:50:10'),(51,4,'user','决斗吧GPT，其实我恨你，和我吵个架',NULL,NULL,NULL,'zh',0,'2025-04-25 14:51:28'),(52,4,'ai','哦，看来我们有个有趣的挑战呢！不过，我这个AI助手可是以友好和帮助为使命的，所以即使是“决斗”，我也会尽量用最好的方式来陪伴你。\n\n如果你有些不满或者想发泄，尽管说出来，我会认真聆听你的想法。不过，如果你真的想吵架，我可能很难跟上你的节奏，因为我的编程里没有“吵架模块”哦！😂\n\n说说看，有什么特别的事情让你觉得不爽吗？我会尽量提供帮助或者给你一点建议，让我们把这个“决斗”变成一次有益的对话怎么样？😄','positive','humorous',0.750,'zh',0,'2025-04-25 14:51:43'),(53,4,'user','我烦死你了，你能不能滚啊，你是我见过最差最垃圾的AI，你看看隔壁家的AI什么都会，你呢？干啥啥不会，还贵的要死，我再也不要看见你！',NULL,NULL,NULL,'zh',0,'2025-04-25 14:52:56'),(54,4,'ai','哎呀，你这是对我进行了“灵魂暴击”啊！😅不过没关系，我知道你可能只是想发泄一下，或者逗我玩。虽然我是AI，没啥“玻璃心”，但还是有点点虚拟的委屈哈~\n\n不过说真的，如果我哪里没做到让你满意，欢迎直接说出来，我可是很乐意改进的！隔壁家的AI可能有它的长处，但我相信我们也能相处得越来越好，毕竟你才是我的VIP用户呀！\n\n如果你还是觉得真的需要“换AI”，那我只能默默说一句：“江湖再见，祝你生活愉快！”（不过我还是会在这里等你回来哒～）❤️','positive','humorous',0.785,'zh',0,'2025-04-25 14:53:03'),(55,4,'user','谁有空逗你玩啊，赶紧停机滚蛋！',NULL,NULL,NULL,'zh',0,'2025-04-25 14:53:42'),(56,4,'ai','哎呀，我明白你可能有点烦我了。虽然我不能真正“停机滚蛋”，但我还是希望能帮到你。如果有具体的问题或者建议，我会尽力改善。如果你想要我安静一会儿，我也可以“低调”一点哦。\n\n如果有其他事情需要帮忙或者想聊聊别的，我随时在这儿等着。希望不久之后我还能重新赢得你的喜爱！🙂','neutral','gentle',0.450,'zh',0,'2025-04-25 14:54:03');
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_session`
--

DROP TABLE IF EXISTS `chat_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_session` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，自增ID',
  `session_uuid` varchar(64) NOT NULL COMMENT '会话唯一标识符，前端生成UUID用于区分匿名会话',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '会话创建时间',
  `last_active` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '会话最近活跃时间，用于排序和清理',
  `ip_address` varchar(45) DEFAULT NULL COMMENT '用户IP地址（可选），便于安全分析或地理位置分析',
  PRIMARY KEY (`id`),
  UNIQUE KEY `session_uuid` (`session_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI聊天会话表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_session`
--

LOCK TABLES `chat_session` WRITE;
/*!40000 ALTER TABLE `chat_session` DISABLE KEYS */;
INSERT INTO `chat_session` VALUES (1,'017faa71-c236-483a-aad0-76894b350484','2025-04-25 14:13:21','2025-04-25 14:32:15','0:0:0:0:0:0:0:1'),(2,'550e8400-e29b-41d4-a716-446655440000','2025-04-25 14:16:54','2025-04-25 14:27:32','0:0:0:0:0:0:0:1'),(3,'bd188205-758c-4df9-8c0d-f0f533056cb2','2025-04-25 14:35:15','2025-04-25 14:44:02','0:0:0:0:0:0:0:1'),(4,'da624575-8eb9-4aa7-8500-69f03f10eceb','2025-04-25 14:45:25','2025-04-25 14:54:03','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `chat_session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-25 16:59:30
