package edu.uark.mobileprogramming.findfamiliar.Model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Characters (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "character") val character: String?,
    @ColumnInfo(name = "character_name") val characterName: String?,
    @ColumnInfo(name = "character_class") val characterClass: String?,
    @ColumnInfo(name = "character_health") val characterHealth: Int?,
    @ColumnInfo(name = "character_armor") val characterArmor: Int?,
    @ColumnInfo(name = "character_level") val characterLevel: Int?,
    @Embedded val characterStats: CharacterStats?

)

data class CharacterStats (
    @ColumnInfo(name = "character_strength") val strength: Int?,
    @ColumnInfo(name = "character_constitution")val constitution: Int?,
    @ColumnInfo(name = "character_wisdom") val wisdom: Int?,
    @ColumnInfo(name = "character_dexterity")val dexterity: Int?,
    @ColumnInfo(name = "character_charisma")val charisma: Int?,
    @ColumnInfo(name = "character_intelligence")val intelligence: Int?,
    @ColumnInfo(name = "character_proficiency_bonus") val proficiencyBonus: Int?
)