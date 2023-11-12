package edu.uark.mobileprogramming.findfamiliar.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "characters",
    foreignKeys = [ForeignKey(entity = CharacterStats::class, parentColumns = ["character_stats"], childColumns = ["stats_id"])])
data class Characters(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "character_id") val characterId: Int,
    @ColumnInfo(name = "character_name") val name: String?,
    @ColumnInfo(name = "character_health") val health: Int?,
    @ColumnInfo(name = "character_armor")val armor: Int?,
    @ColumnInfo(name = "character_class")val className: String?,
    @ColumnInfo(name = "character_level")val level: Int?,
    @ColumnInfo(name = "character_proficiency_bonus")val proficiencyBonus: Int?,
    @ColumnInfo(name = "character_proficiencies")val proficiencies: String?,
    @ColumnInfo(name = "character_stats")val statsId: Int?
)

@Entity(tableName = "character_stats")
data class CharacterStats(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stats_id")val statsId: Int,
    @ColumnInfo(name = "stats_strength")val strength: Int?,
    @ColumnInfo(name = "stats_constitution")val constitution: Int?,
    @ColumnInfo(name = "stats_wisdom")val wisdom: Int?,
    @ColumnInfo(name = "stats_dexterity")val dexterity: Int?,
    @ColumnInfo(name = "stats_charisma")val charisma: Int?,
    @ColumnInfo(name = "stats_intelligence")val intelligence: Int?
)

@Entity(tableName = "character_feats_extras",
    foreignKeys = [ForeignKey(entity = Characters::class, parentColumns = ["character_id"], childColumns = ["feats_character_id"])])
data class CharacterFeatsExtras(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "feats_id")val featId: Int,
    @ColumnInfo(name = "feats_name")val featName: String?,
    @ColumnInfo(name = "feats_description")val featDescription: String?,
    @ColumnInfo(name = "feats_character_id")val characterId: Int
)

@Entity(tableName = "character_weapons",
    foreignKeys = [ForeignKey(entity = Characters::class, parentColumns = ["character_id"], childColumns = ["weapons_character_id"])])
data class CharacterWeapons(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "weapons_id")val weaponId: Int,
    @ColumnInfo(name = "weapons_name")val weaponName: String?,
    @ColumnInfo(name = "weapons_id")val weaponDescription: String?,
    @ColumnInfo(name = "weapons_damage")val weaponDamage: Int?,
    @ColumnInfo(name = "weapons_damage_modifier")val weaponDamageModifier: Int?,
    @ColumnInfo(name = "weapons_attack_modifier")val weaponAttackModifier: Int?,
    @ColumnInfo(name = "weapons_quality")val weaponQuality: Int?,
    @ColumnInfo(name = "weapons_character_id")val characterId: Int?
)
