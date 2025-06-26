package com.corruptdog.efat.gameassets.mesh;

import com.corruptdog.efat.client.CentaurKingMesh;
import com.corruptdog.efat.main.EpicFightUnHumanMob;
import yesman.epicfight.api.client.model.Meshes;

public class UnHumanMobMeshs {

    public static final Meshes.MeshAccessor<CentaurKingMesh> CENTAURKING = Meshes.MeshAccessor.create(EpicFightUnHumanMob.MODID, "entity/centaurking", (jsonModelLoader) -> jsonModelLoader.loadSkinnedMesh(CentaurKingMesh::new));

}
