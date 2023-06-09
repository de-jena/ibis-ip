/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Data In Motion - initial API and implementation 
 */
package de.jena.model.sensinact.ibis.impl;

import de.jena.model.sensinact.ibis.CustomerInfoAllData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData;
import de.jena.model.sensinact.ibis.CustomerInfoTripData;
import de.jena.model.sensinact.ibis.CustomerInfoVehicleData;
import de.jena.model.sensinact.ibis.IbisAdmin;
import de.jena.model.sensinact.ibis.IbisDevice;
import de.jena.model.sensinact.ibis.IbisSensinactPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.sensinact.model.core.provider.impl.ProviderImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ibis Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoAllData <em>Customer Info All Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoCurrentStopIndexData <em>Customer Info Current Stop Index Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoCurrentStopPointData <em>Customer Info Current Stop Point Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoTripData <em>Customer Info Trip Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoVehicleData <em>Customer Info Vehicle Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoCurrentAnnouncementData <em>Customer Info Current Announcement Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoCurrentConnectionData <em>Customer Info Current Connection Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getCustomerInfoCurrentDisplayContentData <em>Customer Info Current Display Content Data</em>}</li>
 *   <li>{@link de.jena.model.sensinact.ibis.impl.IbisDeviceImpl#getIbisAdmin <em>Ibis Admin</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IbisDeviceImpl extends ProviderImpl implements IbisDevice {
	/**
	 * The cached value of the '{@link #getCustomerInfoAllData() <em>Customer Info All Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoAllData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoAllData customerInfoAllData;

	/**
	 * The cached value of the '{@link #getCustomerInfoCurrentStopIndexData() <em>Customer Info Current Stop Index Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoCurrentStopIndexData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoCurrentStopIndexData customerInfoCurrentStopIndexData;

	/**
	 * The cached value of the '{@link #getCustomerInfoCurrentStopPointData() <em>Customer Info Current Stop Point Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoCurrentStopPointData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoCurrentStopPointData customerInfoCurrentStopPointData;

	/**
	 * The cached value of the '{@link #getCustomerInfoTripData() <em>Customer Info Trip Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoTripData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoTripData customerInfoTripData;

	/**
	 * The cached value of the '{@link #getCustomerInfoVehicleData() <em>Customer Info Vehicle Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoVehicleData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoVehicleData customerInfoVehicleData;

	/**
	 * The cached value of the '{@link #getCustomerInfoCurrentAnnouncementData() <em>Customer Info Current Announcement Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoCurrentAnnouncementData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoCurrentAnnouncementData customerInfoCurrentAnnouncementData;

	/**
	 * The cached value of the '{@link #getCustomerInfoCurrentConnectionData() <em>Customer Info Current Connection Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoCurrentConnectionData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoCurrentConnectionData customerInfoCurrentConnectionData;

	/**
	 * The cached value of the '{@link #getCustomerInfoCurrentDisplayContentData() <em>Customer Info Current Display Content Data</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomerInfoCurrentDisplayContentData()
	 * @generated
	 * @ordered
	 */
	protected CustomerInfoCurrentDisplayContentData customerInfoCurrentDisplayContentData;

	/**
	 * The cached value of the '{@link #getIbisAdmin() <em>Ibis Admin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIbisAdmin()
	 * @generated
	 * @ordered
	 */
	protected IbisAdmin ibisAdmin;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IbisDeviceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IbisSensinactPackage.Literals.IBIS_DEVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoAllData getCustomerInfoAllData() {
		return customerInfoAllData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoAllData(CustomerInfoAllData newCustomerInfoAllData, NotificationChain msgs) {
		CustomerInfoAllData oldCustomerInfoAllData = customerInfoAllData;
		customerInfoAllData = newCustomerInfoAllData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA, oldCustomerInfoAllData, newCustomerInfoAllData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoAllData(CustomerInfoAllData newCustomerInfoAllData) {
		if (newCustomerInfoAllData != customerInfoAllData) {
			NotificationChain msgs = null;
			if (customerInfoAllData != null)
				msgs = ((InternalEObject)customerInfoAllData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA, null, msgs);
			if (newCustomerInfoAllData != null)
				msgs = ((InternalEObject)newCustomerInfoAllData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA, null, msgs);
			msgs = basicSetCustomerInfoAllData(newCustomerInfoAllData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA, newCustomerInfoAllData, newCustomerInfoAllData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentStopIndexData getCustomerInfoCurrentStopIndexData() {
		return customerInfoCurrentStopIndexData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoCurrentStopIndexData(CustomerInfoCurrentStopIndexData newCustomerInfoCurrentStopIndexData, NotificationChain msgs) {
		CustomerInfoCurrentStopIndexData oldCustomerInfoCurrentStopIndexData = customerInfoCurrentStopIndexData;
		customerInfoCurrentStopIndexData = newCustomerInfoCurrentStopIndexData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA, oldCustomerInfoCurrentStopIndexData, newCustomerInfoCurrentStopIndexData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoCurrentStopIndexData(CustomerInfoCurrentStopIndexData newCustomerInfoCurrentStopIndexData) {
		if (newCustomerInfoCurrentStopIndexData != customerInfoCurrentStopIndexData) {
			NotificationChain msgs = null;
			if (customerInfoCurrentStopIndexData != null)
				msgs = ((InternalEObject)customerInfoCurrentStopIndexData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA, null, msgs);
			if (newCustomerInfoCurrentStopIndexData != null)
				msgs = ((InternalEObject)newCustomerInfoCurrentStopIndexData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA, null, msgs);
			msgs = basicSetCustomerInfoCurrentStopIndexData(newCustomerInfoCurrentStopIndexData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA, newCustomerInfoCurrentStopIndexData, newCustomerInfoCurrentStopIndexData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentStopPointData getCustomerInfoCurrentStopPointData() {
		return customerInfoCurrentStopPointData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoCurrentStopPointData(CustomerInfoCurrentStopPointData newCustomerInfoCurrentStopPointData, NotificationChain msgs) {
		CustomerInfoCurrentStopPointData oldCustomerInfoCurrentStopPointData = customerInfoCurrentStopPointData;
		customerInfoCurrentStopPointData = newCustomerInfoCurrentStopPointData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA, oldCustomerInfoCurrentStopPointData, newCustomerInfoCurrentStopPointData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoCurrentStopPointData(CustomerInfoCurrentStopPointData newCustomerInfoCurrentStopPointData) {
		if (newCustomerInfoCurrentStopPointData != customerInfoCurrentStopPointData) {
			NotificationChain msgs = null;
			if (customerInfoCurrentStopPointData != null)
				msgs = ((InternalEObject)customerInfoCurrentStopPointData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA, null, msgs);
			if (newCustomerInfoCurrentStopPointData != null)
				msgs = ((InternalEObject)newCustomerInfoCurrentStopPointData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA, null, msgs);
			msgs = basicSetCustomerInfoCurrentStopPointData(newCustomerInfoCurrentStopPointData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA, newCustomerInfoCurrentStopPointData, newCustomerInfoCurrentStopPointData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoTripData getCustomerInfoTripData() {
		return customerInfoTripData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoTripData(CustomerInfoTripData newCustomerInfoTripData, NotificationChain msgs) {
		CustomerInfoTripData oldCustomerInfoTripData = customerInfoTripData;
		customerInfoTripData = newCustomerInfoTripData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA, oldCustomerInfoTripData, newCustomerInfoTripData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoTripData(CustomerInfoTripData newCustomerInfoTripData) {
		if (newCustomerInfoTripData != customerInfoTripData) {
			NotificationChain msgs = null;
			if (customerInfoTripData != null)
				msgs = ((InternalEObject)customerInfoTripData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA, null, msgs);
			if (newCustomerInfoTripData != null)
				msgs = ((InternalEObject)newCustomerInfoTripData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA, null, msgs);
			msgs = basicSetCustomerInfoTripData(newCustomerInfoTripData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA, newCustomerInfoTripData, newCustomerInfoTripData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoVehicleData getCustomerInfoVehicleData() {
		return customerInfoVehicleData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoVehicleData(CustomerInfoVehicleData newCustomerInfoVehicleData, NotificationChain msgs) {
		CustomerInfoVehicleData oldCustomerInfoVehicleData = customerInfoVehicleData;
		customerInfoVehicleData = newCustomerInfoVehicleData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA, oldCustomerInfoVehicleData, newCustomerInfoVehicleData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoVehicleData(CustomerInfoVehicleData newCustomerInfoVehicleData) {
		if (newCustomerInfoVehicleData != customerInfoVehicleData) {
			NotificationChain msgs = null;
			if (customerInfoVehicleData != null)
				msgs = ((InternalEObject)customerInfoVehicleData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA, null, msgs);
			if (newCustomerInfoVehicleData != null)
				msgs = ((InternalEObject)newCustomerInfoVehicleData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA, null, msgs);
			msgs = basicSetCustomerInfoVehicleData(newCustomerInfoVehicleData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA, newCustomerInfoVehicleData, newCustomerInfoVehicleData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentAnnouncementData getCustomerInfoCurrentAnnouncementData() {
		return customerInfoCurrentAnnouncementData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoCurrentAnnouncementData(CustomerInfoCurrentAnnouncementData newCustomerInfoCurrentAnnouncementData, NotificationChain msgs) {
		CustomerInfoCurrentAnnouncementData oldCustomerInfoCurrentAnnouncementData = customerInfoCurrentAnnouncementData;
		customerInfoCurrentAnnouncementData = newCustomerInfoCurrentAnnouncementData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA, oldCustomerInfoCurrentAnnouncementData, newCustomerInfoCurrentAnnouncementData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoCurrentAnnouncementData(CustomerInfoCurrentAnnouncementData newCustomerInfoCurrentAnnouncementData) {
		if (newCustomerInfoCurrentAnnouncementData != customerInfoCurrentAnnouncementData) {
			NotificationChain msgs = null;
			if (customerInfoCurrentAnnouncementData != null)
				msgs = ((InternalEObject)customerInfoCurrentAnnouncementData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA, null, msgs);
			if (newCustomerInfoCurrentAnnouncementData != null)
				msgs = ((InternalEObject)newCustomerInfoCurrentAnnouncementData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA, null, msgs);
			msgs = basicSetCustomerInfoCurrentAnnouncementData(newCustomerInfoCurrentAnnouncementData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA, newCustomerInfoCurrentAnnouncementData, newCustomerInfoCurrentAnnouncementData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentConnectionData getCustomerInfoCurrentConnectionData() {
		return customerInfoCurrentConnectionData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoCurrentConnectionData(CustomerInfoCurrentConnectionData newCustomerInfoCurrentConnectionData, NotificationChain msgs) {
		CustomerInfoCurrentConnectionData oldCustomerInfoCurrentConnectionData = customerInfoCurrentConnectionData;
		customerInfoCurrentConnectionData = newCustomerInfoCurrentConnectionData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA, oldCustomerInfoCurrentConnectionData, newCustomerInfoCurrentConnectionData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoCurrentConnectionData(CustomerInfoCurrentConnectionData newCustomerInfoCurrentConnectionData) {
		if (newCustomerInfoCurrentConnectionData != customerInfoCurrentConnectionData) {
			NotificationChain msgs = null;
			if (customerInfoCurrentConnectionData != null)
				msgs = ((InternalEObject)customerInfoCurrentConnectionData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA, null, msgs);
			if (newCustomerInfoCurrentConnectionData != null)
				msgs = ((InternalEObject)newCustomerInfoCurrentConnectionData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA, null, msgs);
			msgs = basicSetCustomerInfoCurrentConnectionData(newCustomerInfoCurrentConnectionData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA, newCustomerInfoCurrentConnectionData, newCustomerInfoCurrentConnectionData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomerInfoCurrentDisplayContentData getCustomerInfoCurrentDisplayContentData() {
		return customerInfoCurrentDisplayContentData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomerInfoCurrentDisplayContentData(CustomerInfoCurrentDisplayContentData newCustomerInfoCurrentDisplayContentData, NotificationChain msgs) {
		CustomerInfoCurrentDisplayContentData oldCustomerInfoCurrentDisplayContentData = customerInfoCurrentDisplayContentData;
		customerInfoCurrentDisplayContentData = newCustomerInfoCurrentDisplayContentData;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA, oldCustomerInfoCurrentDisplayContentData, newCustomerInfoCurrentDisplayContentData);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCustomerInfoCurrentDisplayContentData(CustomerInfoCurrentDisplayContentData newCustomerInfoCurrentDisplayContentData) {
		if (newCustomerInfoCurrentDisplayContentData != customerInfoCurrentDisplayContentData) {
			NotificationChain msgs = null;
			if (customerInfoCurrentDisplayContentData != null)
				msgs = ((InternalEObject)customerInfoCurrentDisplayContentData).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA, null, msgs);
			if (newCustomerInfoCurrentDisplayContentData != null)
				msgs = ((InternalEObject)newCustomerInfoCurrentDisplayContentData).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA, null, msgs);
			msgs = basicSetCustomerInfoCurrentDisplayContentData(newCustomerInfoCurrentDisplayContentData, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA, newCustomerInfoCurrentDisplayContentData, newCustomerInfoCurrentDisplayContentData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IbisAdmin getIbisAdmin() {
		return ibisAdmin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIbisAdmin(IbisAdmin newIbisAdmin, NotificationChain msgs) {
		IbisAdmin oldIbisAdmin = ibisAdmin;
		ibisAdmin = newIbisAdmin;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN, oldIbisAdmin, newIbisAdmin);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIbisAdmin(IbisAdmin newIbisAdmin) {
		if (newIbisAdmin != ibisAdmin) {
			NotificationChain msgs = null;
			if (ibisAdmin != null)
				msgs = ((InternalEObject)ibisAdmin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN, null, msgs);
			if (newIbisAdmin != null)
				msgs = ((InternalEObject)newIbisAdmin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN, null, msgs);
			msgs = basicSetIbisAdmin(newIbisAdmin, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN, newIbisAdmin, newIbisAdmin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA:
				return basicSetCustomerInfoAllData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA:
				return basicSetCustomerInfoCurrentStopIndexData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA:
				return basicSetCustomerInfoCurrentStopPointData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA:
				return basicSetCustomerInfoTripData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA:
				return basicSetCustomerInfoVehicleData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA:
				return basicSetCustomerInfoCurrentAnnouncementData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA:
				return basicSetCustomerInfoCurrentConnectionData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA:
				return basicSetCustomerInfoCurrentDisplayContentData(null, msgs);
			case IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN:
				return basicSetIbisAdmin(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA:
				return getCustomerInfoAllData();
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA:
				return getCustomerInfoCurrentStopIndexData();
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA:
				return getCustomerInfoCurrentStopPointData();
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA:
				return getCustomerInfoTripData();
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA:
				return getCustomerInfoVehicleData();
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA:
				return getCustomerInfoCurrentAnnouncementData();
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA:
				return getCustomerInfoCurrentConnectionData();
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA:
				return getCustomerInfoCurrentDisplayContentData();
			case IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN:
				return getIbisAdmin();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA:
				setCustomerInfoAllData((CustomerInfoAllData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA:
				setCustomerInfoCurrentStopIndexData((CustomerInfoCurrentStopIndexData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA:
				setCustomerInfoCurrentStopPointData((CustomerInfoCurrentStopPointData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA:
				setCustomerInfoTripData((CustomerInfoTripData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA:
				setCustomerInfoVehicleData((CustomerInfoVehicleData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA:
				setCustomerInfoCurrentAnnouncementData((CustomerInfoCurrentAnnouncementData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA:
				setCustomerInfoCurrentConnectionData((CustomerInfoCurrentConnectionData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA:
				setCustomerInfoCurrentDisplayContentData((CustomerInfoCurrentDisplayContentData)newValue);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN:
				setIbisAdmin((IbisAdmin)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA:
				setCustomerInfoAllData((CustomerInfoAllData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA:
				setCustomerInfoCurrentStopIndexData((CustomerInfoCurrentStopIndexData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA:
				setCustomerInfoCurrentStopPointData((CustomerInfoCurrentStopPointData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA:
				setCustomerInfoTripData((CustomerInfoTripData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA:
				setCustomerInfoVehicleData((CustomerInfoVehicleData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA:
				setCustomerInfoCurrentAnnouncementData((CustomerInfoCurrentAnnouncementData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA:
				setCustomerInfoCurrentConnectionData((CustomerInfoCurrentConnectionData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA:
				setCustomerInfoCurrentDisplayContentData((CustomerInfoCurrentDisplayContentData)null);
				return;
			case IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN:
				setIbisAdmin((IbisAdmin)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_ALL_DATA:
				return customerInfoAllData != null;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_INDEX_DATA:
				return customerInfoCurrentStopIndexData != null;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_STOP_POINT_DATA:
				return customerInfoCurrentStopPointData != null;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_TRIP_DATA:
				return customerInfoTripData != null;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_VEHICLE_DATA:
				return customerInfoVehicleData != null;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_ANNOUNCEMENT_DATA:
				return customerInfoCurrentAnnouncementData != null;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_CONNECTION_DATA:
				return customerInfoCurrentConnectionData != null;
			case IbisSensinactPackage.IBIS_DEVICE__CUSTOMER_INFO_CURRENT_DISPLAY_CONTENT_DATA:
				return customerInfoCurrentDisplayContentData != null;
			case IbisSensinactPackage.IBIS_DEVICE__IBIS_ADMIN:
				return ibisAdmin != null;
		}
		return super.eIsSet(featureID);
	}

} //IbisDeviceImpl
