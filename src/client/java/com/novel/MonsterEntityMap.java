package com.novel;

import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;

/**
 * MonsterEntityMap
 */
public class MonsterEntityMap {
  private ClientWorld currentWorld = null;
  private MinecraftClient currentClient = MinecraftClient.getInstance();
  private ClientPlayerEntity player = null;
  private Box playerBox = null;
  private TypeFilter<Entity, HostileEntity> hostileFilter =
      TypeFilter.instanceOf(HostileEntity.class);
  private TypeFilter<Entity, ZombieEntity> zombieFilter = TypeFilter.instanceOf(ZombieEntity.class);
  private TypeFilter<Entity, ZoglinEntity> zoglinFilter = TypeFilter.instanceOf(ZoglinEntity.class);
  private TypeFilter<Entity, WitherEntity> witherFilter = TypeFilter.instanceOf(WitherEntity.class);
  private TypeFilter<Entity, WardenEntity> wardenFilter = TypeFilter.instanceOf(WardenEntity.class);
  private TypeFilter<Entity, VexEntity> vexFilter = TypeFilter.instanceOf(VexEntity.class);
  private TypeFilter<Entity, SpiderEntity> spiderFilter = TypeFilter.instanceOf(SpiderEntity.class);
  private TypeFilter<Entity, SilverfishEntity> silverfishFilter =
      TypeFilter.instanceOf(SilverfishEntity.class);
  private TypeFilter<Entity, PatrolEntity> patrikFilter = TypeFilter.instanceOf(PatrolEntity.class);
  private TypeFilter<Entity, GuardianEntity> guardianFilter =
      TypeFilter.instanceOf(GuardianEntity.class);
  private TypeFilter<Entity, GiantEntity> giantFilter = TypeFilter.instanceOf(GiantEntity.class);
  private TypeFilter<Entity, EndermiteEntity> endermiteFilter =
      TypeFilter.instanceOf(EndermiteEntity.class);
  private TypeFilter<Entity, EndermanEntity> endermanFilter =
      TypeFilter.instanceOf(EndermanEntity.class);
  private TypeFilter<Entity, CreeperEntity> creeperFilter =
      TypeFilter.instanceOf(CreeperEntity.class);
  private TypeFilter<Entity, BlazeEntity> blazeFilter = TypeFilter.instanceOf(BlazeEntity.class);
  private TypeFilter<Entity, AbstractSkeletonEntity> abstractSkeletonFilter =
      TypeFilter.instanceOf(AbstractSkeletonEntity.class);
  private TypeFilter<Entity, AbstractPiglinEntity> abstractPiglinFilter =
      TypeFilter.instanceOf(AbstractPiglinEntity.class);
  private volatile MonsterRecord monsterRecord = null;

  private Box getPlayerBox(ClientPlayerEntity player, int size) {
    return new Box(player.getPos().getX() - size, player.getPos().getY(),
        player.getPos().getZ() - size, player.getPos().getX() + size, player.getPos().getY() + size,
        player.getPos().getZ() + size);
  }

  private <T extends Entity> List<T> getEntityList(TypeFilter<Entity, T> filter) {
    List<T> res = currentWorld.getEntitiesByType(filter, playerBox, EntityPredicates.VALID_ENTITY);
    return res;
  }


  public void setWorld(ClientWorld world) {
    this.currentWorld = world;
  }

  public void update() {
    player = currentClient.player;
    playerBox = getPlayerBox(player, Config.get().getPlayerDetectSize());
    monsterRecord = new MonsterRecord(getEntityList(hostileFilter).size(),
        getEntityList(zombieFilter).size(), getEntityList(zoglinFilter).size(),
        getEntityList(witherFilter).size(), getEntityList(wardenFilter).size(),
        getEntityList(vexFilter).size(), getEntityList(spiderFilter).size(),
        getEntityList(silverfishFilter).size(), getEntityList(patrikFilter).size(),
        getEntityList(guardianFilter).size(), getEntityList(giantFilter).size(),
        getEntityList(endermiteFilter).size(), getEntityList(endermanFilter).size(),
        getEntityList(creeperFilter).size(), getEntityList(blazeFilter).size(),
        getEntityList(abstractSkeletonFilter).size(), getEntityList(abstractPiglinFilter).size());
  }

  public MonsterEntityMap(ClientWorld world) {
    setWorld(world);
  }

  public MonsterEntityMap() {}

  public MonsterRecord getMonsterRecord() {
    return this.monsterRecord;
  }

  public void clearRecord() {
    this.monsterRecord = null;
  }

}
