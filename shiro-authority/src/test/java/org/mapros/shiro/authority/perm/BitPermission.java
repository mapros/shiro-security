package org.mapros.shiro.authority.perm;

import org.apache.shiro.authz.Permission;

/**
 * rule:
 * +resourceIdentify+permissionBit+instanceId
 * permission:
 * 0 all
 * 1 create 0001
 * 2 update 0010
 * 4 delete 0100
 * 8 view   1000
 * @author mapros
 */
public class BitPermission implements Permission {

    private String resourceIdentify;
    private int permissionBit;
    private String instanceId;

    public BitPermission(String permissionString) {
        String[] array = permissionString.split("\\+");
        if (array.length > 1) {
            resourceIdentify = array[1];
        }
        if (isEmpty(resourceIdentify)) {
            resourceIdentify = "*";
        }
        if (array.length > 2) {
            permissionBit = Integer.valueOf(array[2]);
        }
        if (array.length > 3) {
            instanceId = array[3];
        }
        if (isEmpty(instanceId)) {
            instanceId = "*";
        }
    }

    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof BitPermission)) {
            return false;
        }
        BitPermission other = (BitPermission) p;

        return ("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify)) && (this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0) && ("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId));

    }

    @Override
    public String toString() {
        return "BitPermission{" +
                "resourceIdentify='" + resourceIdentify + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }

    private boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
