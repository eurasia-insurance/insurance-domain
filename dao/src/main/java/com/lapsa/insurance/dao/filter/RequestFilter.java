package com.lapsa.insurance.dao.filter;

import java.time.Instant;

import com.lapsa.insurance.domain.crm.User;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.elements.ObtainingMethod;
import com.lapsa.insurance.elements.ObtainingStatus;
import com.lapsa.insurance.elements.PaymentMethod;
import com.lapsa.insurance.elements.PaymentStatus;
import com.lapsa.insurance.elements.ProgressStatus;
import com.lapsa.insurance.elements.RequestSource;
import com.lapsa.insurance.elements.RequestStatus;
import com.lapsa.insurance.elements.TransactionProblem;
import com.lapsa.insurance.elements.TransactionStatus;

public interface RequestFilter {

    // Request properties

    Integer getId();

    String getRequesterNameMask();

    String getRequesterIdNumberMask();

    RequestSource getRequestSource();
    
    RequestStatus getRequestStatus();

    ProgressStatus getProgressStatus();

    Instant getCreatedAfter();

    Instant getCreatedBefore();

    Instant getCompletedAfter();

    Instant getCompletedBefore();

    User getCreatedBy();

    User getAcceptedBy();

    User getCompletedBy();

    User getClosedBy();

    // InsuranceRequest properties

    InsuranceRequestType getRequestType();

    PaymentMethod getPaymentMethod();

    PaymentStatus getPaymentStatus();

    ObtainingMethod getObtainingMethod();

    ObtainingStatus getObtainingStatus();

    TransactionStatus getTransactionStatus();

    String getAgreementNumberMask();

    TransactionProblem getTransactionProblem();

}
