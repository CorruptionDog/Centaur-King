package com.corruptdog.efat.model.armature;

import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.model.Armature;

import java.util.Map;

public class CentaurKingArmature extends Armature {
    public final Joint b_calf_l_ik;
    public final Joint b_calf_r_ik;
    public final Joint f_foot_l_ik;
    public final Joint f_foot_r_ik;
    public final Joint b_foot_r_ik;
    public final Joint b_foot_l_ik;
    public final Joint ik_001;
    public final Joint ik;
    public final Joint root_003;
    public final Joint b_thigh_r;
    public final Joint b_leg_r;
    public final Joint b_calf_r;
    public final Joint b_foot_r;
    public final Joint b_thigh_l;
    public final Joint b_leg_l;
    public final Joint b_calf_l;
    public final Joint b_foot_l;
    public final Joint root_002;
    public final Joint root_001;
    public final Joint f_thigh_l;
    public final Joint f_leg_l;
    public final Joint f_foot_l;
    public final Joint f_r_l;
    public final Joint f_thigh_r;
    public final Joint f_leg_r;
    public final Joint f_foot_r;
    public final Joint f_r_r;
    public final Joint root_004;
    public final Joint root_005;
    public final Joint root_006;
    public final Joint head;
    public final Joint jian_l;
    public final Joint up_arm_l;
    public final Joint dm_arm_l;
    public final Joint hand_l;
    public final Joint jian_r;
    public final Joint up_arm_r;
    public final Joint dm_arm_r;
    public final Joint hand_r;
    public final Joint weapon;
    public final Joint ik_002;
    public final Joint ik_003;
    public final Joint f_leg_l_ik;
    public final Joint f_leg_r_ik;






    public CentaurKingArmature(String name, int jointNumber, Joint rootJoint, Map<String, Joint> jointMap) {
        super(name, jointNumber, rootJoint, jointMap);
        this.b_calf_l_ik = this.getOrLogException(jointMap, "B_Calf_L_IK");
        this.b_calf_r_ik = this.getOrLogException(jointMap, "B_Calf_R_IK");
        this.f_foot_l_ik = this.getOrLogException(jointMap, "F_Foot_L_IK");
        this.f_foot_r_ik = this.getOrLogException(jointMap, "F_Foot_R_IK");
        this.b_foot_r_ik = this.getOrLogException(jointMap, "B_Foot_R_IK");
        this.b_foot_l_ik = this.getOrLogException(jointMap, "B_Foot_L_IK");
        this.ik_001 = this.getOrLogException(jointMap, "IK.001");
        this.ik = this.getOrLogException(jointMap, "IK");
        this.root_003 = this.getOrLogException(jointMap, "Root.003");
        this.b_thigh_r = this.getOrLogException(jointMap, "B_Thigh_R");
        this.b_leg_r = this.getOrLogException(jointMap, "B_Leg_R");
        this.b_calf_r = this.getOrLogException(jointMap, "B_Calf_R");
        this.b_foot_r = this.getOrLogException(jointMap, "B_Foot_R");
        this.b_thigh_l = this.getOrLogException(jointMap, "B_Thigh_L");
        this.b_leg_l = this.getOrLogException(jointMap, "B_Leg_L");
        this.b_calf_l = this.getOrLogException(jointMap, "B_Calf_L");
        this.b_foot_l = this.getOrLogException(jointMap, "B_Foot_L");
        this.root_002 = this.getOrLogException(jointMap, "Root.002");
        this.root_001 = this.getOrLogException(jointMap, "Root.001");
        this.f_thigh_l = this.getOrLogException(jointMap, "F_Thigh_L");
        this.f_leg_l = this.getOrLogException(jointMap, "F_Leg_L");
        this.f_foot_l = this.getOrLogException(jointMap, "F_Foot_L");
        this.f_r_l = this.getOrLogException(jointMap, "F_r_L");
        this.f_thigh_r = this.getOrLogException(jointMap, "F_Thigh_R");
        this.f_leg_r = this.getOrLogException(jointMap, "F_Leg_R");
        this.f_foot_r = this.getOrLogException(jointMap, "F_Foot_R");
        this.f_r_r = this.getOrLogException(jointMap, "F_r_R");
        this.root_004 = this.getOrLogException(jointMap, "Root.004");
        this.root_005 = this.getOrLogException(jointMap, "Root.005");
        this.root_006 = this.getOrLogException(jointMap, "Root.006");
        this.head = this.getOrLogException(jointMap, "Head");
        this.jian_l = this.getOrLogException(jointMap, "jian_L");
        this.up_arm_l = this.getOrLogException(jointMap, "UP_Arm_L");
        this.dm_arm_l = this.getOrLogException(jointMap, "DM_Arm_L");
        this.hand_l = this.getOrLogException(jointMap, "HAND_L");
        this.jian_r = this.getOrLogException(jointMap, "jian_R");
        this.up_arm_r = this.getOrLogException(jointMap, "UP_Arm_R");
        this.dm_arm_r = this.getOrLogException(jointMap, "DM_Arm_R");
        this.hand_r = this.getOrLogException(jointMap, "HAND_R");
        this.weapon = this.getOrLogException(jointMap, "Weapon");
        this.ik_002 = this.getOrLogException(jointMap, "IK.002");
        this.ik_003 = this.getOrLogException(jointMap, "IK.003");
        this.f_leg_l_ik = this.getOrLogException(jointMap, "F_Leg_L_IK");
        this.f_leg_r_ik = this.getOrLogException(jointMap, "F_Leg_R_IK");
    }
}
