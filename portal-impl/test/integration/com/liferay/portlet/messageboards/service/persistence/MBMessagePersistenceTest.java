/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AggregateTestRule;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.test.LiferayIntegrationTestRule;
import com.liferay.portal.test.PersistenceTestRule;
import com.liferay.portal.test.TransactionalTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.test.RandomTestUtil;

import com.liferay.portlet.messageboards.NoSuchMessageException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.impl.MBMessageModelImpl;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
public class MBMessagePersistenceTest {
	@Rule
	public final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@After
	public void tearDown() throws Exception {
		Iterator<MBMessage> iterator = _mbMessages.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMessage mbMessage = _persistence.create(pk);

		Assert.assertNotNull(mbMessage);

		Assert.assertEquals(mbMessage.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MBMessage newMBMessage = addMBMessage();

		_persistence.remove(newMBMessage);

		MBMessage existingMBMessage = _persistence.fetchByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertNull(existingMBMessage);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMBMessage();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMessage newMBMessage = _persistence.create(pk);

		newMBMessage.setUuid(RandomTestUtil.randomString());

		newMBMessage.setGroupId(RandomTestUtil.nextLong());

		newMBMessage.setCompanyId(RandomTestUtil.nextLong());

		newMBMessage.setUserId(RandomTestUtil.nextLong());

		newMBMessage.setUserName(RandomTestUtil.randomString());

		newMBMessage.setCreateDate(RandomTestUtil.nextDate());

		newMBMessage.setModifiedDate(RandomTestUtil.nextDate());

		newMBMessage.setClassNameId(RandomTestUtil.nextLong());

		newMBMessage.setClassPK(RandomTestUtil.nextLong());

		newMBMessage.setCategoryId(RandomTestUtil.nextLong());

		newMBMessage.setThreadId(RandomTestUtil.nextLong());

		newMBMessage.setRootMessageId(RandomTestUtil.nextLong());

		newMBMessage.setParentMessageId(RandomTestUtil.nextLong());

		newMBMessage.setSubject(RandomTestUtil.randomString());

		newMBMessage.setBody(RandomTestUtil.randomString());

		newMBMessage.setFormat(RandomTestUtil.randomString());

		newMBMessage.setAnonymous(RandomTestUtil.randomBoolean());

		newMBMessage.setPriority(RandomTestUtil.nextDouble());

		newMBMessage.setAllowPingbacks(RandomTestUtil.randomBoolean());

		newMBMessage.setAnswer(RandomTestUtil.randomBoolean());

		newMBMessage.setStatus(RandomTestUtil.nextInt());

		newMBMessage.setStatusByUserId(RandomTestUtil.nextLong());

		newMBMessage.setStatusByUserName(RandomTestUtil.randomString());

		newMBMessage.setStatusDate(RandomTestUtil.nextDate());

		_mbMessages.add(_persistence.update(newMBMessage));

		MBMessage existingMBMessage = _persistence.findByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertEquals(existingMBMessage.getUuid(), newMBMessage.getUuid());
		Assert.assertEquals(existingMBMessage.getMessageId(),
			newMBMessage.getMessageId());
		Assert.assertEquals(existingMBMessage.getGroupId(),
			newMBMessage.getGroupId());
		Assert.assertEquals(existingMBMessage.getCompanyId(),
			newMBMessage.getCompanyId());
		Assert.assertEquals(existingMBMessage.getUserId(),
			newMBMessage.getUserId());
		Assert.assertEquals(existingMBMessage.getUserName(),
			newMBMessage.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMessage.getCreateDate()),
			Time.getShortTimestamp(newMBMessage.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMessage.getModifiedDate()),
			Time.getShortTimestamp(newMBMessage.getModifiedDate()));
		Assert.assertEquals(existingMBMessage.getClassNameId(),
			newMBMessage.getClassNameId());
		Assert.assertEquals(existingMBMessage.getClassPK(),
			newMBMessage.getClassPK());
		Assert.assertEquals(existingMBMessage.getCategoryId(),
			newMBMessage.getCategoryId());
		Assert.assertEquals(existingMBMessage.getThreadId(),
			newMBMessage.getThreadId());
		Assert.assertEquals(existingMBMessage.getRootMessageId(),
			newMBMessage.getRootMessageId());
		Assert.assertEquals(existingMBMessage.getParentMessageId(),
			newMBMessage.getParentMessageId());
		Assert.assertEquals(existingMBMessage.getSubject(),
			newMBMessage.getSubject());
		Assert.assertEquals(existingMBMessage.getBody(), newMBMessage.getBody());
		Assert.assertEquals(existingMBMessage.getFormat(),
			newMBMessage.getFormat());
		Assert.assertEquals(existingMBMessage.getAnonymous(),
			newMBMessage.getAnonymous());
		AssertUtils.assertEquals(existingMBMessage.getPriority(),
			newMBMessage.getPriority());
		Assert.assertEquals(existingMBMessage.getAllowPingbacks(),
			newMBMessage.getAllowPingbacks());
		Assert.assertEquals(existingMBMessage.getAnswer(),
			newMBMessage.getAnswer());
		Assert.assertEquals(existingMBMessage.getStatus(),
			newMBMessage.getStatus());
		Assert.assertEquals(existingMBMessage.getStatusByUserId(),
			newMBMessage.getStatusByUserId());
		Assert.assertEquals(existingMBMessage.getStatusByUserName(),
			newMBMessage.getStatusByUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMessage.getStatusDate()),
			Time.getShortTimestamp(newMBMessage.getStatusDate()));
	}

	@Test
	public void testCountByUuid() {
		try {
			_persistence.countByUuid(StringPool.BLANK);

			_persistence.countByUuid(StringPool.NULL);

			_persistence.countByUuid((String)null);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUUID_G() {
		try {
			_persistence.countByUUID_G(StringPool.BLANK,
				RandomTestUtil.nextLong());

			_persistence.countByUUID_G(StringPool.NULL, 0L);

			_persistence.countByUUID_G((String)null, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUuid_C() {
		try {
			_persistence.countByUuid_C(StringPool.BLANK,
				RandomTestUtil.nextLong());

			_persistence.countByUuid_C(StringPool.NULL, 0L);

			_persistence.countByUuid_C((String)null, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByGroupId() {
		try {
			_persistence.countByGroupId(RandomTestUtil.nextLong());

			_persistence.countByGroupId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByCompanyId() {
		try {
			_persistence.countByCompanyId(RandomTestUtil.nextLong());

			_persistence.countByCompanyId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUserId() {
		try {
			_persistence.countByUserId(RandomTestUtil.nextLong());

			_persistence.countByUserId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByThreadId() {
		try {
			_persistence.countByThreadId(RandomTestUtil.nextLong());

			_persistence.countByThreadId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByThreadReplies() {
		try {
			_persistence.countByThreadReplies(RandomTestUtil.nextLong());

			_persistence.countByThreadReplies(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_U() {
		try {
			_persistence.countByG_U(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByG_U(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C() {
		try {
			_persistence.countByG_C(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByG_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_S() {
		try {
			_persistence.countByG_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByG_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_S() {
		try {
			_persistence.countByC_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByC_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C() {
		try {
			_persistence.countByU_C(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByU_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_CArrayable() {
		try {
			_persistence.countByU_C(RandomTestUtil.nextLong(),
				new long[] { RandomTestUtil.nextLong(), 0L });
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_C() {
		try {
			_persistence.countByC_C(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByC_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByT_P() {
		try {
			_persistence.countByT_P(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByT_P(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByT_A() {
		try {
			_persistence.countByT_A(RandomTestUtil.nextLong(),
				RandomTestUtil.randomBoolean());

			_persistence.countByT_A(0L, RandomTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByT_S() {
		try {
			_persistence.countByT_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByT_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByTR_S() {
		try {
			_persistence.countByTR_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByTR_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_U_S() {
		try {
			_persistence.countByG_U_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

			_persistence.countByG_U_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_T() {
		try {
			_persistence.countByG_C_T(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

			_persistence.countByG_C_T(0L, 0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_S() {
		try {
			_persistence.countByG_C_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

			_persistence.countByG_C_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_C() {
		try {
			_persistence.countByU_C_C(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

			_persistence.countByU_C_C(0L, 0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_S() {
		try {
			_persistence.countByU_C_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

			_persistence.countByU_C_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_SArrayable() {
		try {
			_persistence.countByU_C_S(RandomTestUtil.nextLong(),
				new long[] { RandomTestUtil.nextLong(), 0L },
				RandomTestUtil.nextInt());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_C_S() {
		try {
			_persistence.countByC_C_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

			_persistence.countByC_C_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_T_A() {
		try {
			_persistence.countByG_C_T_A(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
				RandomTestUtil.randomBoolean());

			_persistence.countByG_C_T_A(0L, 0L, 0L,
				RandomTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_T_S() {
		try {
			_persistence.countByG_C_T_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByG_C_T_S(0L, 0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_C_S() {
		try {
			_persistence.countByU_C_C_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByU_C_C_S(0L, 0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MBMessage newMBMessage = addMBMessage();

		MBMessage existingMBMessage = _persistence.findByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertEquals(existingMBMessage, newMBMessage);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchMessageException");
		}
		catch (NoSuchMessageException nsee) {
		}
	}

	@Test
	public void testFindAll() throws Exception {
		try {
			_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFilterFindByGroupId() throws Exception {
		try {
			_persistence.filterFindByGroupId(0, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator<MBMessage> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MBMessage", "uuid", true,
			"messageId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "categoryId", true,
			"threadId", true, "rootMessageId", true, "parentMessageId", true,
			"subject", true, "body", true, "format", true, "anonymous", true,
			"priority", true, "allowPingbacks", true, "answer", true, "status",
			true, "statusByUserId", true, "statusByUserName", true,
			"statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MBMessage newMBMessage = addMBMessage();

		MBMessage existingMBMessage = _persistence.fetchByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertEquals(existingMBMessage, newMBMessage);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMessage missingMBMessage = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMBMessage);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MBMessage newMBMessage1 = addMBMessage();
		MBMessage newMBMessage2 = addMBMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMBMessage1.getPrimaryKey());
		primaryKeys.add(newMBMessage2.getPrimaryKey());

		Map<Serializable, MBMessage> mbMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mbMessages.size());
		Assert.assertEquals(newMBMessage1,
			mbMessages.get(newMBMessage1.getPrimaryKey()));
		Assert.assertEquals(newMBMessage2,
			mbMessages.get(newMBMessage2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MBMessage> mbMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mbMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MBMessage newMBMessage = addMBMessage();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMBMessage.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MBMessage> mbMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mbMessages.size());
		Assert.assertEquals(newMBMessage,
			mbMessages.get(newMBMessage.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MBMessage> mbMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mbMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MBMessage newMBMessage = addMBMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMBMessage.getPrimaryKey());

		Map<Serializable, MBMessage> mbMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mbMessages.size());
		Assert.assertEquals(newMBMessage,
			mbMessages.get(newMBMessage.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MBMessageLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					MBMessage mbMessage = (MBMessage)object;

					Assert.assertNotNull(mbMessage);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MBMessage newMBMessage = addMBMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("messageId",
				newMBMessage.getMessageId()));

		List<MBMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MBMessage existingMBMessage = result.get(0);

		Assert.assertEquals(existingMBMessage, newMBMessage);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("messageId",
				RandomTestUtil.nextLong()));

		List<MBMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MBMessage newMBMessage = addMBMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("messageId"));

		Object newMessageId = newMBMessage.getMessageId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("messageId",
				new Object[] { newMessageId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMessageId = result.get(0);

		Assert.assertEquals(existingMessageId, newMessageId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("messageId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("messageId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		MBMessage newMBMessage = addMBMessage();

		_persistence.clearCache();

		MBMessageModelImpl existingMBMessageModelImpl = (MBMessageModelImpl)_persistence.findByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertTrue(Validator.equals(
				existingMBMessageModelImpl.getUuid(),
				existingMBMessageModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingMBMessageModelImpl.getGroupId(),
			existingMBMessageModelImpl.getOriginalGroupId());
	}

	protected MBMessage addMBMessage() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMessage mbMessage = _persistence.create(pk);

		mbMessage.setUuid(RandomTestUtil.randomString());

		mbMessage.setGroupId(RandomTestUtil.nextLong());

		mbMessage.setCompanyId(RandomTestUtil.nextLong());

		mbMessage.setUserId(RandomTestUtil.nextLong());

		mbMessage.setUserName(RandomTestUtil.randomString());

		mbMessage.setCreateDate(RandomTestUtil.nextDate());

		mbMessage.setModifiedDate(RandomTestUtil.nextDate());

		mbMessage.setClassNameId(RandomTestUtil.nextLong());

		mbMessage.setClassPK(RandomTestUtil.nextLong());

		mbMessage.setCategoryId(RandomTestUtil.nextLong());

		mbMessage.setThreadId(RandomTestUtil.nextLong());

		mbMessage.setRootMessageId(RandomTestUtil.nextLong());

		mbMessage.setParentMessageId(RandomTestUtil.nextLong());

		mbMessage.setSubject(RandomTestUtil.randomString());

		mbMessage.setBody(RandomTestUtil.randomString());

		mbMessage.setFormat(RandomTestUtil.randomString());

		mbMessage.setAnonymous(RandomTestUtil.randomBoolean());

		mbMessage.setPriority(RandomTestUtil.nextDouble());

		mbMessage.setAllowPingbacks(RandomTestUtil.randomBoolean());

		mbMessage.setAnswer(RandomTestUtil.randomBoolean());

		mbMessage.setStatus(RandomTestUtil.nextInt());

		mbMessage.setStatusByUserId(RandomTestUtil.nextLong());

		mbMessage.setStatusByUserName(RandomTestUtil.randomString());

		mbMessage.setStatusDate(RandomTestUtil.nextDate());

		_mbMessages.add(_persistence.update(mbMessage));

		return mbMessage;
	}

	private List<MBMessage> _mbMessages = new ArrayList<MBMessage>();
	private MBMessagePersistence _persistence = MBMessageUtil.getPersistence();
}