package com.lapsa.insurance.messenger;

import java.util.List;

import com.lapsa.insurance.domain.PolicyExpressOrder;

public interface PolicyExpressOrderUserNotifier {
    void notifyUser(PolicyExpressOrder policyExpressOrder);
    void notifyUser(List<PolicyExpressOrder> policyExpressOrder);
}