/**
 * Project Name: datadisplay-server
 * File Name: MongodbOperationImpl.java
 * Package Name: com.tianque.datadisplay.server.util.database.impl
 * Date: 2019年11月13日 下午1:54:00
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.util.database.impl;

import java.util.regex.Pattern;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tianque.commons.util.core.base.StringUtil;
import com.tianque.datadisplay.server.util.database.AbstractDatabaseOperation;

/**
 * @author : 孙留平
 * @since : 2019年11月13日 下午1:54:00
 * @see :
 */
public class MongodbOperationImpl extends AbstractDatabaseOperation {
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(MongodbOperationImpl.class);

	public MongoClient getMongoClient() {
		LOGGER.info("初始化Mongo");
		Builder options = new MongoClientOptions.Builder();
		// 连接池设置300个
		options.connectionsPerHost(300);
		// 连接超时 3000ms至少
		options.connectTimeout(15000);
		options.maxWaitTime(5000);
		// 套接字超时时间 0 无限制
		options.socketTimeout(0);

		// 线程队列数，如果连接线程排满了。会抛出错误
		options.threadsAllowedToBlockForConnectionMultiplier(5000);

		options.writeConcern(WriteConcern.ACKNOWLEDGED);

		MongoClient mongoClient = null;
		if (StringUtil.isStringAvaliable(this.getDatabaseInfo().getUsername())
		        && StringUtil.isStringAvaliable(
		                this.getDatabaseInfo().getPassword())) {
			MongoCredential mongoCredential = MongoCredential.createCredential(
			        this.getDatabaseInfo().getUsername(),
			        this.getDatabaseInfo().getInstanceName(),
			        this.getDatabaseInfo().getPassword().toCharArray());
			options.sslEnabled(false);
			mongoClient = new MongoClient(
			        new ServerAddress(this.getDatabaseInfo().getDatabaseIp(),
			                this.getDatabaseInfo().getPort()),
			        mongoCredential, options.build());
		} else {
			mongoClient = new MongoClient(
			        new ServerAddress(this.getDatabaseInfo().getDatabaseIp(),
			                this.getDatabaseInfo().getPort()),
			        options.build());
		}

		return mongoClient;
	}

	public MongoDatabase getMongoDatabase(String databaseName) {
		return getMongoClient().getDatabase(databaseName);
	}

	public MongoDatabase getMongoDatabase() {
		return getMongoClient()
		        .getDatabase(this.getDatabaseInfo().getInstanceName());
	}

	public MongoCollection<Document> getCollection(MongoDatabase mongoDatabase,
	        String collectionName) {
		return mongoDatabase.getCollection(collectionName);
	}

	public MongoCollection<Document> getCollection(String collectionName) {
		return getMongoDatabase().getCollection(collectionName);
	}

	public FindIterable<Document> getDocumentsFromCollection(
	        String collectionName) {
		return getCollection(collectionName).find();
	}

	public FindIterable<Document> getDocumentsFromCollection(
	        String collectionName, BasicDBObject basicDBObject) {
		return getCollection(collectionName).find(basicDBObject);
	}

	public FindIterable<Document> getDocumentsFromCollection(
	        String collectionName, String key, String value) {
		return getCollection(collectionName)
		        .find(new BasicDBObject(key, value));
	}

	public FindIterable<Document> getDocumentsFromCollectionWithPattern(
	        String collectionName, String key, Pattern pattern) {
		return getCollection(collectionName)
		        .find(new BasicDBObject(key, pattern));
	}

	public FindIterable<Document> getDocumentsFromCollectionWithPattern(
	        String collectionName, String key, String compilePattern) {
		return getCollection(collectionName)
		        .find(new BasicDBObject(key, Pattern.compile(compilePattern)));
	}

	public FindIterable<Document> getDocumentsFromCollection(
	        String collectionName, String executeSql) {

		return getCollection(collectionName).find();
	}

	public FindIterable<Document> getDocumentsFromSql(String executeSql) {
		String[] sqlSplit = executeSql.split("\\.");
		return getMongoDatabase(sqlSplit[0]).getCollection(sqlSplit[1]).find();
	}

	public static void main(String[] args) {
		// MongodbOperationImpl mongodbOperationImpl = new MongodbOperationImpl();
		// DatabaseInfo databaseInfo = new DatabaseInfo();
		// databaseInfo.setDatabaseIp("localhost");
		// databaseInfo.setPort(27017);
		// databaseInfo.setDatabaseName("测试本地数据库");
		// databaseInfo.setInstanceName("local");
		// // databaseInfo.setUsername("root");
		// // databaseInfo.setPassword("Admin@1234");
		// mongodbOperationImpl.setDatabaseInfo(databaseInfo);
		// FindIterable<Document> dataListFindIterable = mongodbOperationImpl
		// .getCollection("startup_log").find();
		//
		// for (Document document : dataListFindIterable) {
		// System.out.println(document.toJson());
		// }

	}
}
