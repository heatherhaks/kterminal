@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

@Suppress("SpellCheckingInspection")
object KTerminalColor {
    internal val colorMap = mapOf("WHITE" to Color(1f, 1f, 1f, 1f),
            "LIGHT_GRAY" to Color(-0x40404001),
            "GRAY" to Color(0x7f7f7fff),
            "DARK_GRAY" to Color(0x3f3f3fff),
            "BLACK" to Color(0f, 0f, 0f, 1f),
            "CLEAR" to Color(0f, 0f, 0f, 0f),
            "BLUE" to Color(0f, 0f, 1f, 1f),
            "NAVY" to Color(0f, 0f, 0.5f, 1f),
            "ROYAL" to Color(0x4169e1ff),
            "SLATE" to Color(0x708090ff),
            "SKY" to Color(-0x78311401),
            "CYAN" to Color(0f, 1f, 1f, 1f),
            "TEAL" to Color(0f, 0.5f, 0.5f, 1f),
            "GREEN" to Color(0x00ff00ff),
            "CHARTREUSE" to Color(0x7fff00ff),
            "LIME" to Color(0x32cd32ff),
            "FOREST" to Color(0x228b22ff),
            "OLIVE" to Color(0x6b8e23ff),
            "YELLOW" to Color(-0xff01),
            "GOLD" to Color(-0x28ff01),
            "GOLDENROD" to Color(-0x255adf01),
            "ORANGE" to Color(-0x5aff01),
            "BROWN" to Color(-0x74baec01),
            "TAN" to Color(-0x2d4b7301),
            "FIREBRICK" to Color(-0x4ddddd01),
            "RED" to Color(-0xffff01),
            "SCARLET" to Color(-0xcbe301),
            "CORAL" to Color(-0x80af01),
            "SALMON" to Color(-0x57f8d01),
            "PINK" to Color(-0x964b01),
            "MAGENTA" to Color(1f, 0f, 1f, 1f),
            "PURPLE" to Color(-0x5fdf0f01),
            "VIOLET" to Color(-0x117d1101),
            "MAROON" to Color(-0x4fcf9f01),
            "ABSINTHE" to Color(0x9BF046),
            "AIR_FORCE_BLUE" to Color(0x578FC7),
            "AMAZONITE" to Color(0x8FC7C7),
            "ANGEL_WING" to Color(0xE1F8FA),
            "APPLE_GREEN" to Color(0x14E60A),
            "APRICOT" to Color(0xFFA53C),
            "AQUAMARINE" to Color(0xC7F1F1),
            "ARMY_GREEN" to Color(0x626200),
            "ARTICHOKE" to Color(0x87B48E),
            "ASPARAGUS" to Color(0x587D3E),
            "AVOCADO" to Color(0x6AA805),
            "BANANA_PUDDING" to Color(0xE6D55A),
            "BEIGE" to Color(0xCBAA89),
            "BLACKLIGHT_GLOW" to Color(0x8732D2),
            "BLUE_EYE" to Color(0x4B7DC8),
            "BLUE_SMOKE" to Color(0x8FABC7),
            "BLUE_STEEL" to Color(0x7676CA),
            "BLUEBERRY" to Color(0x0F377D),
            "BOLOGNA" to Color(0x98344D),
            "BOYSENBERRY" to Color(0x57578F),
            "BRICK" to Color(0xD5524A),
            "BRONZE_SKIN_1" to Color(0x73573B),
            "BRONZE_SKIN_2" to Color(0x8F7357),
            "BRONZE_SKIN_3" to Color(0xC49E73),
            "BRONZE_SKIN_4" to Color(0xE3C7AB),
            "BROWN_VELVET" to Color(0x4B2837),
            "BUBBLE" to Color(0xBFFFFF),
            "BUBBLE_GUM" to Color(0xFF50BF),
            "BUBBLEGUM_PINK" to Color(0xFD81FF),
            "BURNT_YELLOW" to Color(0xD79B0F),
            "CALM_SKY" to Color(0x4AA4FF),
            "CARMINE" to Color(0xBD1039),
            "CEDAR_WOOD" to Color(0x621800),
            "CELADON" to Color(0xAFFFAF),
            "CELERY" to Color(0x7DFF73),
            "CHARTREUSE_2" to Color(0xC8FF41),
            "CHERRY_SYRUP" to Color(0x551937),
            "CHINCHILLA" to Color(0x929292),
            "CHIPPED_GRANITE" to Color(0xA8B9DC),
            "CLOUD" to Color(0xEDEDED),
            "COAL_BLACK" to Color(0x131313),
            "COLD_IRON" to Color(0x57738F),
            "CORN_SILK" to Color(0xC7C78F),
            "CORNFLOWER_BLUE" to Color(0x3C3CF5),
            "COTTON_CANDY" to Color(0xFFDCF5),
            "CREAM" to Color(0xFFFFBF),
            "CRICKET" to Color(0x8F8F57),
            "CYAN_2" to Color(0x00FFFF),
            "DARK_PINE" to Color(0x283405),
            "DARK_SKIN_1" to Color(0x573B3B),
            "DARK_SKIN_2" to Color(0x73413C),
            "DARK_SKIN_3" to Color(0x8E5555),
            "DARK_TEAL" to Color(0x234146),
            "DEEP_JUNGLE" to Color(0x191E0F),
            "DEEP_PURPLE" to Color(0x7F007F),
            "DEEP_TEAL" to Color(0x055A5C),
            "DENIM_BLUE" to Color(0x109CDE),
            "DESERT_RAIN" to Color(0x326496),
            "DRAB_GREEN" to Color(0x414123),
            "DREARY_BLUE" to Color(0x3B5773),
            "DRIED_SAGE" to Color(0xABE3C5),
            "DRIFTWOOD" to Color(0x8C805A),
            "DRY_BRUSH" to Color(0xAC9400),
            "DRY_PEPPER" to Color(0xDA6E0A),
            "DULL_AZURE" to Color(0x2378DC),
            "DULL_GREEN" to Color(0x53500A),
            "DULL_VIOLET" to Color(0x73578F),
            "DUN" to Color(0xB5B572),
            "DUST_BUNNY" to Color(0xBED2F0),
            "DUSTY_GRAPE" to Color(0x724072),
            "DUSTY_GREEN" to Color(0x73AB73),
            "DUSTY_PINK" to Color(0xC78FB9),
            "EARWAX" to Color(0xBFBF3F),
            "EGGPLANT" to Color(0x320A46),
            "ELECTRIC_BLUE" to Color(0x0052F6),
            "ELEPHANT" to Color(0x808080),
            "EMBERS" to Color(0xF55A32),
            "EMERALD" to Color(0x00C514),
            "EMINENCE" to Color(0x3C233C),
            "EUCALYPTUS" to Color(0x1C8C4E),
            "FADED_BLUE" to Color(0x3F3FBF),
            "FAWN" to Color(0xBF7F3F),
            "FEATHER_DOWN" to Color(0xE3E3FF),
            "FERN_GREEN" to Color(0x0C5C0C),
            "FLAMINGO" to Color(0xFC3A8C),
            "FLORAL_FOAM" to Color(0x0F6946),
            "FOG" to Color(0xC9C9C9),
            "FOREST_GLEN" to Color(0x149605),
            "FRESH_BLOOD" to Color(0xFF0000),
            "FROG_GREEN" to Color(0x73C805),
            "FRUIT_PUNCH" to Color(0xC80078),
            "FUSION_RED" to Color(0xFF3C0A),
            "GARTER_SNAKE" to Color(0x64C082),
            "GRAPE_LOLLIPOP" to Color(0x5A187B),
            "GRAPE_SODA" to Color(0x410062),
            "GRAPHITE" to Color(0x373737),
            "GRAY_GREEN" to Color(0x506450),
            "GREYHOUND" to Color(0xA4A4A4),
            "HAM" to Color(0xEBACE1),
            "HELIOTROPE" to Color(0xFF52FF),
            "HIDDEN_BLUE" to Color(0x186ABD),
            "HONEYDEW" to Color(0xC7E3AB),
            "HOSPITAL_GREEN" to Color(0x3FBFBF),
            "HOT_SAUCE" to Color(0xA5140A),
            "HUNTER_GREEN" to Color(0x1E2D23),
            "INDIGO" to Color(0x231094),
            "INFECTION" to Color(0x96DC19),
            "IOLITE" to Color(0x494973),
            "IRIS" to Color(0x6241F6),
            "IRON" to Color(0x6E6E6E),
            "IVY_GREEN" to Color(0x007F00),
            "JADE" to Color(0x3FBF3F),
            "JUICY_GRAPE" to Color(0x6010D0),
            "KELLY_GREEN" to Color(0x578F57),
            "KOA" to Color(0xA04B05),
            "KYANITE" to Color(0x3B7373),
            "LAVENDER" to Color(0xB991FF),
            "LEAD" to Color(0x5B5B5B),
            "LEMON" to Color(0xFFFF00),
            "LIGHT_PLUM" to Color(0xDA20E0),
            "LIGHT_SKIN_1" to Color(0x7E6E60),
            "LIGHT_SKIN_2" to Color(0xA0695F),
            "LIGHT_SKIN_3" to Color(0xC07872),
            "LIGHT_SKIN_4" to Color(0xD08A74),
            "LIGHT_SKIN_5" to Color(0xE19B7D),
            "LIGHT_SKIN_6" to Color(0xEBAA8C),
            "LIGHT_SKIN_7" to Color(0xF5B99B),
            "LIGHT_SKIN_8" to Color(0xF6C8AF),
            "LIGHT_SKIN_9" to Color(0xF5E1D2),
            "LILAC" to Color(0xD7A5FF),
            "LIPSTICK" to Color(0xE61E78),
            "LIZARD_SCALES" to Color(0x73733B),
            "LURID_RED" to Color(0xDA2010),
            "MAGENTA_2" to Color(0xF500F5),
            "MAIDENHAIR_FERN" to Color(0x3B7349),
            "MALACHITE" to Color(0x0AD70A),
            "MARSH" to Color(0x738F57),
            "MAUVE" to Color(0xAB73AB),
            "MEDIUM_PLUM" to Color(0xBD10C5),
            "MEDIUM_TEAL" to Color(0x06C491),
            "MILD_VIOLET" to Color(0xAB8FC7),
            "MINT_GREEN" to Color(0x4BF05A),
            "MOSS_GREEN" to Color(0x204608),
            "MULBERRY" to Color(0x641464),
            "MUMMY_BROWN" to Color(0x7F3F00),
            "MURK" to Color(0x0F192D),
            "MUSH" to Color(0xB1B10A),
            "NAVY_BLUE" to Color(0x162C52),
            "NIGHTSHADE" to Color(0x321623),
            "NINJA" to Color(0x1F1F3B),
            "OCEAN_BLUE" to Color(0x00007F),
            "OCHRE" to Color(0x5F3214),
            "OLD_ROSE" to Color(0xD7A0BE),
            "OLIVE_GREEN" to Color(0x3B573B),
            "OLIVE_OIL" to Color(0xA2A255),
            "ORANGE_2" to Color(0xFF7F00),
            "ORCHID" to Color(0xBD62FF),
            "PASTEL_SKY" to Color(0xABE3E3),
            "PATINA" to Color(0xABC7E3),
            "PEA_SOUP" to Color(0x8EBE55),
            "PEACH" to Color(0xFFBF81),
            "PEAT_BOG" to Color(0x465032),
            "PENCIL_YELLOW" to Color(0xFFEA4A),
            "PERIWINKLE" to Color(0x8181FF),
            "PINE_GREEN" to Color(0x235037),
            "PINK_LEMONADE" to Color(0xFF6AC5),
            "PINK_SKIN_1" to Color(0xAB7373),
            "PINK_SKIN_2" to Color(0xC78F8F),
            "PINK_SKIN_3" to Color(0xE3ABAB),
            "PINK_SKIN_4" to Color(0xF8D2DA),
            "PINK_TUTU" to Color(0xF8C6FC),
            "PINK_VIOLET" to Color(0x8F578F),
            "PISTACHIO" to Color(0xA2D8A2),
            "PITCH_BLACK" to Color(0x010101),
            "PLATINUM" to Color(0xDBDBDB),
            "PLUM_JUICE" to Color(0xA01982),
            "POLISHED_SAPPHIRE" to Color(0x101CDA),
            "PORK_CHOP" to Color(0xFFC0CB),
            "POWDER_BLUE" to Color(0x5AC5FF),
            "PRASE" to Color(0x05B450),
            "PRUNE" to Color(0x463246),
            "PRUSSIAN_BLUE" to Color(0x004A9C),
            "PUCE" to Color(0xBCAFC0),
            "PURPLE_FREESIA" to Color(0x9C41FF),
            "PUTTY" to Color(0xBF3F3F),
            "RASPBERRY" to Color(0x911437),
            "RAW_MEAT" to Color(0xFF8181),
            "REDWOOD" to Color(0xB45A00),
            "REFRESHING_MIST" to Color(0x91EBFF),
            "RIPE_PLUM" to Color(0xAB57AB),
            "RIPPED_DENIM" to Color(0x699DC3),
            "ROBIN_EGG_BLUE" to Color(0x08DED5),
            "ROSEATE_SPOONBILL" to Color(0xC87DA0),
            "ROUGH_SAPPHIRE" to Color(0x4A5AFF),
            "ROYAL_BLUE" to Color(0x0010BD),
            "ROYAL_VIOLET" to Color(0x573B73),
            "SAFFRON" to Color(0xFFD510),
            "SAGE_GREEN" to Color(0xB4EECA),
            "SALMON_2" to Color(0xFF6262),
            "SCRIBE_INK" to Color(0x280A1E),
            "SEAFOAM" to Color(0x3CFEA5),
            "SEAL_BROWN" to Color(0x551414),
            "SEAWATER" to Color(0x007F7F),
            "SHADOW" to Color(0x252525),
            "SHAMROCK_GREEN" to Color(0x00FF00),
            "SHARP_AZURE" to Color(0x007FFF),
            "SHINING_SKY" to Color(0x55E6FF),
            "SHINING_WHITE" to Color(0xFFFFFF),
            "SHRIMP" to Color(0xFAA0B9),
            "SIENNA" to Color(0x7F0000),
            "SILVER" to Color(0xB6B6B6),
            "SILVER_GREEN" to Color(0x8FC78F),
            "SILVER_PINK" to Color(0xE3C7E3),
            "SLATE_GRAY" to Color(0x6E8287),
            "SLOW_CREEK" to Color(0x7E9494),
            "SMOG" to Color(0xABABE3),
            "SMOKE" to Color(0x494949),
            "SOAP" to Color(0xD7C3FA),
            "SOFT_TEAL" to Color(0x129880),
            "SPACE_BLUE" to Color(0x0C2148),
            "SPEARMINT" to Color(0x64ABAB),
            "SPRING_GREEN" to Color(0x00DE6A),
            "STEAM" to Color(0x7DD7F0),
            "STRAW" to Color(0xEDEDC7),
            "STRONG_CYAN" to Color(0x00BFFF),
            "STYGIAN_BLUE" to Color(0x0F0F50),
            "SUBTLETY" to Color(0x786EF0),
            "SUDS" to Color(0xBEB9FA),
            "TAN_2" to Color(0xDADAAB),
            "TARNISH" to Color(0xABC78F),
            "TAUPE" to Color(0x3B2D1F),
            "TAXICAB_YELLOW" to Color(0xF6BD31),
            "TEA_ROSE" to Color(0xE1B9D2),
            "THICK_AMETHYST" to Color(0x5010B0),
            "THIN_AMETHYST" to Color(0x7F00FF),
            "THISTLE" to Color(0xE673FF),
            "THULIAN_PINK" to Color(0xC35A91),
            "TRANSPARENT" to Color(0x000000),
            "TROPIC_MIST" to Color(0xD0DAF8),
            "TURQUOISE" to Color(0x2DEBA8),
            "TWILIGHT_CLOUD" to Color(0x8F8FC7),
            "TYRIAN_PURPLE" to Color(0xBF3FBF),
            "ULTRAMARINE" to Color(0x0000FF),
            "UMBER" to Color(0x7F7F00),
            "VAPOR" to Color(0x90B0FF),
            "VARISCITE" to Color(0x6AFFCD),
            "VARNISH" to Color(0x401811),
            "VIOLET_2" to Color(0x8C14BE),
            "VIOLET_CUSHIONS" to Color(0x8F57C7),
            "VIRIDIAN" to Color(0x507D5F),
            "WATERCOLOR_BLACK" to Color(0x3B3B57),
            "WATERCOLOR_GRAY" to Color(0x736EAA),
            "WET_STONE" to Color(0xA6A090),
            "WISTERIA" to Color(0xBD29FF),
            "WOODLANDS" to Color(0x3C6E14),
            "ZUCCHINI" to Color(0x123832))

    val WHITE: Color = colorMap["WHITE"] ?: Color.WHITE
    val LIGHT_GRAY: Color = colorMap["LIGHT_GRAY"] ?: Color.WHITE
    val GRAY: Color = colorMap["GRAY"] ?: Color.WHITE
    val DARK_GRAY: Color = colorMap["DARK_GRAY"] ?: Color.WHITE
    val BLACK: Color = colorMap["BLACK"] ?: Color.WHITE
    val CLEAR: Color = colorMap["CLEAR"] ?: Color.WHITE
    val BLUE: Color = colorMap["BLUE"] ?: Color.WHITE
    val NAVY: Color = colorMap["NAVY"] ?: Color.WHITE
    val ROYAL: Color = colorMap["ROYAL"] ?: Color.WHITE
    val SLATE: Color = colorMap["SLATE"] ?: Color.WHITE
    val SKY: Color = colorMap["SKY"] ?: Color.WHITE
    val CYAN: Color = colorMap["CYAN"] ?: Color.WHITE
    val TEAL: Color = colorMap["TEAL"] ?: Color.WHITE
    val GREEN: Color = colorMap["GREEN"] ?: Color.WHITE
    val CHARTREUSE: Color = colorMap["CHARTREUSE"] ?: Color.WHITE
    val LIME: Color = colorMap["LIME"] ?: Color.WHITE
    val FOREST: Color = colorMap["FOREST"] ?: Color.WHITE
    val OLIVE: Color = colorMap["OLIVE"] ?: Color.WHITE
    val YELLOW: Color = colorMap["YELLOW"] ?: Color.WHITE
    val GOLD: Color = colorMap["GOLD"] ?: Color.WHITE
    val GOLDENROD: Color = colorMap["GOLDENROD"] ?: Color.WHITE
    val ORANGE: Color = colorMap["ORANGE"] ?: Color.WHITE
    val BROWN: Color = colorMap["BROWN"] ?: Color.WHITE
    val TAN: Color = colorMap["TAN"] ?: Color.WHITE
    val FIREBRICK: Color = colorMap["FIREBRICK"] ?: Color.WHITE
    val RED: Color = colorMap["RED"] ?: Color.WHITE
    val SCARLET: Color = colorMap["SCARLET"] ?: Color.WHITE
    val CORAL: Color = colorMap["CORAL"] ?: Color.WHITE
    val SALMON: Color = colorMap["SALMON"] ?: Color.WHITE
    val PINK: Color = colorMap["PINK"] ?: Color.WHITE
    val MAGENTA: Color = colorMap["MAGENTA"] ?: Color.WHITE
    val PURPLE: Color = colorMap["PURPLE"] ?: Color.WHITE
    val VIOLET: Color = colorMap["VIOLET"] ?: Color.WHITE
    val MAROON: Color = colorMap["MAROON"] ?: Color.WHITE
    val ABSINTHE: Color = colorMap["ABSINTHE"] ?: Color.WHITE
    val AIR_FORCE_BLUE: Color = colorMap["AIR_FORCE_BLUE"] ?: Color.WHITE
    val AMAZONITE: Color = colorMap["AMAZONITE"] ?: Color.WHITE
    val ANGEL_WING: Color = colorMap["ANGEL_WING"] ?: Color.WHITE
    val APPLE_GREEN: Color = colorMap["APPLE_GREEN"] ?: Color.WHITE
    val APRICOT: Color = colorMap["APRICOT"] ?: Color.WHITE
    val AQUAMARINE: Color = colorMap["AQUAMARINE"] ?: Color.WHITE
    val ARMY_GREEN: Color = colorMap["ARMY_GREEN"] ?: Color.WHITE
    val ARTICHOKE: Color = colorMap["ARTICHOKE"] ?: Color.WHITE
    val ASPARAGUS: Color = colorMap["ASPARAGUS"] ?: Color.WHITE
    val AVOCADO: Color = colorMap["AVOCADO"] ?: Color.WHITE
    val BANANA_PUDDING: Color = colorMap["BANANA_PUDDING"] ?: Color.WHITE
    val BEIGE: Color = colorMap["BEIGE"] ?: Color.WHITE
    val BLACKLIGHT_GLOW: Color = colorMap["BLACKLIGHT_GLOW"] ?: Color.WHITE
    val BLUE_EYE: Color = colorMap["BLUE_EYE"] ?: Color.WHITE
    val BLUE_SMOKE: Color = colorMap["BLUE_SMOKE"] ?: Color.WHITE
    val BLUE_STEEL: Color = colorMap["BLUE_STEEL"] ?: Color.WHITE
    val BLUEBERRY: Color = colorMap["BLUEBERRY"] ?: Color.WHITE
    val BOLOGNA: Color = colorMap["BOLOGNA"] ?: Color.WHITE
    val BOYSENBERRY: Color = colorMap["BOYSENBERRY"] ?: Color.WHITE
    val BRICK: Color = colorMap["BRICK"] ?: Color.WHITE
    val BRONZE_SKIN_1: Color = colorMap["BRONZE_SKIN_1"] ?: Color.WHITE
    val BRONZE_SKIN_2: Color = colorMap["BRONZE_SKIN_2"] ?: Color.WHITE
    val BRONZE_SKIN_3: Color = colorMap["BRONZE_SKIN_3"] ?: Color.WHITE
    val BRONZE_SKIN_4: Color = colorMap["BRONZE_SKIN_4"] ?: Color.WHITE
    val BROWN_VELVET: Color = colorMap["BROWN_VELVET"] ?: Color.WHITE
    val BUBBLE: Color = colorMap["BUBBLE"] ?: Color.WHITE
    val BUBBLE_GUM: Color = colorMap["BUBBLE_GUM"] ?: Color.WHITE
    val BUBBLEGUM_PINK: Color = colorMap["BUBBLEGUM_PINK"] ?: Color.WHITE
    val BURNT_YELLOW: Color = colorMap["BURNT_YELLOW"] ?: Color.WHITE
    val CALM_SKY: Color = colorMap["CALM_SKY"] ?: Color.WHITE
    val CARMINE: Color = colorMap["CARMINE"] ?: Color.WHITE
    val CEDAR_WOOD: Color = colorMap["CEDAR_WOOD"] ?: Color.WHITE
    val CELADON: Color = colorMap["CELADON"] ?: Color.WHITE
    val CELERY: Color = colorMap["CELERY"] ?: Color.WHITE
    val CHARTREUSE_2: Color = colorMap["CHARTREUSE_2"] ?: Color.WHITE
    val CHERRY_SYRUP: Color = colorMap["CHERRY_SYRUP"] ?: Color.WHITE
    val CHINCHILLA: Color = colorMap["CHINCHILLA"] ?: Color.WHITE
    val CHIPPED_GRANITE: Color = colorMap["CHIPPED_GRANITE"] ?: Color.WHITE
    val CLOUD: Color = colorMap["CLOUD"] ?: Color.WHITE
    val COAL_BLACK: Color = colorMap["COAL_BLACK"] ?: Color.WHITE
    val COLD_IRON: Color = colorMap["COLD_IRON"] ?: Color.WHITE
    val CORN_SILK: Color = colorMap["CORN_SILK"] ?: Color.WHITE
    val CORNFLOWER_BLUE: Color = colorMap["CORNFLOWER_BLUE"] ?: Color.WHITE
    val COTTON_CANDY: Color = colorMap["COTTON_CANDY"] ?: Color.WHITE
    val CREAM: Color = colorMap["CREAM"] ?: Color.WHITE
    val CRICKET: Color = colorMap["CRICKET"] ?: Color.WHITE
    val CYAN_2: Color = colorMap["CYAN_2"] ?: Color.WHITE
    val DARK_PINE: Color = colorMap["DARK_PINE"] ?: Color.WHITE
    val DARK_SKIN_1: Color = colorMap["DARK_SKIN_1"] ?: Color.WHITE
    val DARK_SKIN_2: Color = colorMap["DARK_SKIN_2"] ?: Color.WHITE
    val DARK_SKIN_3: Color = colorMap["DARK_SKIN_3"] ?: Color.WHITE
    val DARK_TEAL: Color = colorMap["DARK_TEAL"] ?: Color.WHITE
    val DEEP_JUNGLE: Color = colorMap["DEEP_JUNGLE"] ?: Color.WHITE
    val DEEP_PURPLE: Color = colorMap["DEEP_PURPLE"] ?: Color.WHITE
    val DEEP_TEAL: Color = colorMap["DEEP_TEAL"] ?: Color.WHITE
    val DENIM_BLUE: Color = colorMap["DENIM_BLUE"] ?: Color.WHITE
    val DESERT_RAIN: Color = colorMap["DESERT_RAIN"] ?: Color.WHITE
    val DRAB_GREEN: Color = colorMap["DRAB_GREEN"] ?: Color.WHITE
    val DREARY_BLUE: Color = colorMap["DREARY_BLUE"] ?: Color.WHITE
    val DRIED_SAGE: Color = colorMap["DRIED_SAGE"] ?: Color.WHITE
    val DRIFTWOOD: Color = colorMap["DRIFTWOOD"] ?: Color.WHITE
    val DRY_BRUSH: Color = colorMap["DRY_BRUSH"] ?: Color.WHITE
    val DRY_PEPPER: Color = colorMap["DRY_PEPPER"] ?: Color.WHITE
    val DULL_AZURE: Color = colorMap["DULL_AZURE"] ?: Color.WHITE
    val DULL_GREEN: Color = colorMap["DULL_GREEN"] ?: Color.WHITE
    val DULL_VIOLET: Color = colorMap["DULL_VIOLET"] ?: Color.WHITE
    val DUN: Color = colorMap["DUN"] ?: Color.WHITE
    val DUST_BUNNY: Color = colorMap["DUST_BUNNY"] ?: Color.WHITE
    val DUSTY_GRAPE: Color = colorMap["DUSTY_GRAPE"] ?: Color.WHITE
    val DUSTY_GREEN: Color = colorMap["DUSTY_GREEN"] ?: Color.WHITE
    val DUSTY_PINK: Color = colorMap["DUSTY_PINK"] ?: Color.WHITE
    val EARWAX: Color = colorMap["EARWAX"] ?: Color.WHITE
    val EGGPLANT: Color = colorMap["EGGPLANT"] ?: Color.WHITE
    val ELECTRIC_BLUE: Color = colorMap["ELECTRIC_BLUE"] ?: Color.WHITE
    val ELEPHANT: Color = colorMap["ELEPHANT"] ?: Color.WHITE
    val EMBERS: Color = colorMap["EMBERS"] ?: Color.WHITE
    val EMERALD: Color = colorMap["EMERALD"] ?: Color.WHITE
    val EMINENCE: Color = colorMap["EMINENCE"] ?: Color.WHITE
    val EUCALYPTUS: Color = colorMap["EUCALYPTUS"] ?: Color.WHITE
    val FADED_BLUE: Color = colorMap["FADED_BLUE"] ?: Color.WHITE
    val FAWN: Color = colorMap["FAWN"] ?: Color.WHITE
    val FEATHER_DOWN: Color = colorMap["FEATHER_DOWN"] ?: Color.WHITE
    val FERN_GREEN: Color = colorMap["FERN_GREEN"] ?: Color.WHITE
    val FLAMINGO: Color = colorMap["FLAMINGO"] ?: Color.WHITE
    val FLORAL_FOAM: Color = colorMap["FLORAL_FOAM"] ?: Color.WHITE
    val FOG: Color = colorMap["FOG"] ?: Color.WHITE
    val FOREST_GLEN: Color = colorMap["FOREST_GLEN"] ?: Color.WHITE
    val FRESH_BLOOD: Color = colorMap["FRESH_BLOOD"] ?: Color.WHITE
    val FROG_GREEN: Color = colorMap["FROG_GREEN"] ?: Color.WHITE
    val FRUIT_PUNCH: Color = colorMap["FRUIT_PUNCH"] ?: Color.WHITE
    val FUSION_RED: Color = colorMap["FUSION_RED"] ?: Color.WHITE
    val GARTER_SNAKE: Color = colorMap["GARTER_SNAKE"] ?: Color.WHITE
    val GRAPE_LOLLIPOP: Color = colorMap["GRAPE_LOLLIPOP"] ?: Color.WHITE
    val GRAPE_SODA: Color = colorMap["GRAPE_SODA"] ?: Color.WHITE
    val GRAPHITE: Color = colorMap["GRAPHITE"] ?: Color.WHITE
    val GRAY_GREEN: Color = colorMap["GRAY_GREEN"] ?: Color.WHITE
    val GREYHOUND: Color = colorMap["GREYHOUND"] ?: Color.WHITE
    val HAM: Color = colorMap["HAM"] ?: Color.WHITE
    val HELIOTROPE: Color = colorMap["HELIOTROPE"] ?: Color.WHITE
    val HIDDEN_BLUE: Color = colorMap["HIDDEN_BLUE"] ?: Color.WHITE
    val HONEYDEW: Color = colorMap["HONEYDEW"] ?: Color.WHITE
    val HOSPITAL_GREEN: Color = colorMap["HOSPITAL_GREEN"] ?: Color.WHITE
    val HOT_SAUCE: Color = colorMap["HOT_SAUCE"] ?: Color.WHITE
    val HUNTER_GREEN: Color = colorMap["HUNTER_GREEN"] ?: Color.WHITE
    val INDIGO: Color = colorMap["INDIGO"] ?: Color.WHITE
    val INFECTION: Color = colorMap["INFECTION"] ?: Color.WHITE
    val IOLITE: Color = colorMap["IOLITE"] ?: Color.WHITE
    val IRIS: Color = colorMap["IRIS"] ?: Color.WHITE
    val IRON: Color = colorMap["IRON"] ?: Color.WHITE
    val IVY_GREEN: Color = colorMap["IVY_GREEN"] ?: Color.WHITE
    val JADE: Color = colorMap["JADE"] ?: Color.WHITE
    val JUICY_GRAPE: Color = colorMap["JUICY_GRAPE"] ?: Color.WHITE
    val KELLY_GREEN: Color = colorMap["KELLY_GREEN"] ?: Color.WHITE
    val KOA: Color = colorMap["KOA"] ?: Color.WHITE
    val KYANITE: Color = colorMap["KYANITE"] ?: Color.WHITE
    val LAVENDER: Color = colorMap["LAVENDER"] ?: Color.WHITE
    val LEAD: Color = colorMap["LEAD"] ?: Color.WHITE
    val LEMON: Color = colorMap["LEMON"] ?: Color.WHITE
    val LIGHT_PLUM: Color = colorMap["LIGHT_PLUM"] ?: Color.WHITE
    val LIGHT_SKIN_1: Color = colorMap["LIGHT_SKIN_1"] ?: Color.WHITE
    val LIGHT_SKIN_2: Color = colorMap["LIGHT_SKIN_2"] ?: Color.WHITE
    val LIGHT_SKIN_3: Color = colorMap["LIGHT_SKIN_3"] ?: Color.WHITE
    val LIGHT_SKIN_4: Color = colorMap["LIGHT_SKIN_4"] ?: Color.WHITE
    val LIGHT_SKIN_5: Color = colorMap["LIGHT_SKIN_5"] ?: Color.WHITE
    val LIGHT_SKIN_6: Color = colorMap["LIGHT_SKIN_6"] ?: Color.WHITE
    val LIGHT_SKIN_7: Color = colorMap["LIGHT_SKIN_7"] ?: Color.WHITE
    val LIGHT_SKIN_8: Color = colorMap["LIGHT_SKIN_8"] ?: Color.WHITE
    val LIGHT_SKIN_9: Color = colorMap["LIGHT_SKIN_9"] ?: Color.WHITE
    val LILAC: Color = colorMap["LILAC"] ?: Color.WHITE
    val LIPSTICK: Color = colorMap["LIPSTICK"] ?: Color.WHITE
    val LIZARD_SCALES: Color = colorMap["LIZARD_SCALES"] ?: Color.WHITE
    val LURID_RED: Color = colorMap["LURID_RED"] ?: Color.WHITE
    val MAGENTA_2: Color = colorMap["MAGENTA_2"] ?: Color.WHITE
    val MAIDENHAIR_FERN: Color = colorMap["MAIDENHAIR_FERN"] ?: Color.WHITE
    val MALACHITE: Color = colorMap["MALACHITE"] ?: Color.WHITE
    val MARSH: Color = colorMap["MARSH"] ?: Color.WHITE
    val MAUVE: Color = colorMap["MAUVE"] ?: Color.WHITE
    val MEDIUM_PLUM: Color = colorMap["MEDIUM_PLUM"] ?: Color.WHITE
    val MEDIUM_TEAL: Color = colorMap["MEDIUM_TEAL"] ?: Color.WHITE
    val MILD_VIOLET: Color = colorMap["MILD_VIOLET"] ?: Color.WHITE
    val MINT_GREEN: Color = colorMap["MINT_GREEN"] ?: Color.WHITE
    val MOSS_GREEN: Color = colorMap["MOSS_GREEN"] ?: Color.WHITE
    val MULBERRY: Color = colorMap["MULBERRY"] ?: Color.WHITE
    val MUMMY_BROWN: Color = colorMap["MUMMY_BROWN"] ?: Color.WHITE
    val MURK: Color = colorMap["MURK"] ?: Color.WHITE
    val MUSH: Color = colorMap["MUSH"] ?: Color.WHITE
    val NAVY_BLUE: Color = colorMap["NAVY_BLUE"] ?: Color.WHITE
    val NIGHTSHADE: Color = colorMap["NIGHTSHADE"] ?: Color.WHITE
    val NINJA: Color = colorMap["NINJA"] ?: Color.WHITE
    val OCEAN_BLUE: Color = colorMap["OCEAN_BLUE"] ?: Color.WHITE
    val OCHRE: Color = colorMap["OCHRE"] ?: Color.WHITE
    val OLD_ROSE: Color = colorMap["OLD_ROSE"] ?: Color.WHITE
    val OLIVE_GREEN: Color = colorMap["OLIVE_GREEN"] ?: Color.WHITE
    val OLIVE_OIL: Color = colorMap["OLIVE_OIL"] ?: Color.WHITE
    val ORANGE_2: Color = colorMap["ORANGE_2"] ?: Color.WHITE
    val ORCHID: Color = colorMap["ORCHID"] ?: Color.WHITE
    val PASTEL_SKY: Color = colorMap["PASTEL_SKY"] ?: Color.WHITE
    val PATINA: Color = colorMap["PATINA"] ?: Color.WHITE
    val PEA_SOUP: Color = colorMap["PEA_SOUP"] ?: Color.WHITE
    val PEACH: Color = colorMap["PEACH"] ?: Color.WHITE
    val PEAT_BOG: Color = colorMap["PEAT_BOG"] ?: Color.WHITE
    val PENCIL_YELLOW: Color = colorMap["PENCIL_YELLOW"] ?: Color.WHITE
    val PERIWINKLE: Color = colorMap["PERIWINKLE"] ?: Color.WHITE
    val PINE_GREEN: Color = colorMap["PINE_GREEN"] ?: Color.WHITE
    val PINK_LEMONADE: Color = colorMap["PINK_LEMONADE"] ?: Color.WHITE
    val PINK_SKIN_1: Color = colorMap["PINK_SKIN_1"] ?: Color.WHITE
    val PINK_SKIN_2: Color = colorMap["PINK_SKIN_2"] ?: Color.WHITE
    val PINK_SKIN_3: Color = colorMap["PINK_SKIN_3"] ?: Color.WHITE
    val PINK_SKIN_4: Color = colorMap["PINK_SKIN_4"] ?: Color.WHITE
    val PINK_TUTU: Color = colorMap["PINK_TUTU"] ?: Color.WHITE
    val PINK_VIOLET: Color = colorMap["PINK_VIOLET"] ?: Color.WHITE
    val PISTACHIO: Color = colorMap["PISTACHIO"] ?: Color.WHITE
    val PITCH_BLACK: Color = colorMap["PITCH_BLACK"] ?: Color.WHITE
    val PLATINUM: Color = colorMap["PLATINUM"] ?: Color.WHITE
    val PLUM_JUICE: Color = colorMap["PLUM_JUICE"] ?: Color.WHITE
    val POLISHED_SAPPHIRE: Color = colorMap["POLISHED_SAPPHIRE"] ?: Color.WHITE
    val PORK_CHOP: Color = colorMap["PORK_CHOP"] ?: Color.WHITE
    val POWDER_BLUE: Color = colorMap["POWDER_BLUE"] ?: Color.WHITE
    val PRASE: Color = colorMap["PRASE"] ?: Color.WHITE
    val PRUNE: Color = colorMap["PRUNE"] ?: Color.WHITE
    val PRUSSIAN_BLUE: Color = colorMap["PRUSSIAN_BLUE"] ?: Color.WHITE
    val PUCE: Color = colorMap["PUCE"] ?: Color.WHITE
    val PURPLE_FREESIA: Color = colorMap["PURPLE_FREESIA"] ?: Color.WHITE
    val PUTTY: Color = colorMap["PUTTY"] ?: Color.WHITE
    val RASPBERRY: Color = colorMap["RASPBERRY"] ?: Color.WHITE
    val RAW_MEAT: Color = colorMap["RAW_MEAT"] ?: Color.WHITE
    val REDWOOD: Color = colorMap["REDWOOD"] ?: Color.WHITE
    val REFRESHING_MIST: Color = colorMap["REFRESHING_MIST"] ?: Color.WHITE
    val RIPE_PLUM: Color = colorMap["RIPE_PLUM"] ?: Color.WHITE
    val RIPPED_DENIM: Color = colorMap["RIPPED_DENIM"] ?: Color.WHITE
    val ROBIN_EGG_BLUE: Color = colorMap["ROBIN_EGG_BLUE"] ?: Color.WHITE
    val ROSEATE_SPOONBILL: Color = colorMap["ROSEATE_SPOONBILL"] ?: Color.WHITE
    val ROUGH_SAPPHIRE: Color = colorMap["ROUGH_SAPPHIRE"] ?: Color.WHITE
    val ROYAL_BLUE: Color = colorMap["ROYAL_BLUE"] ?: Color.WHITE
    val ROYAL_VIOLET: Color = colorMap["ROYAL_VIOLET"] ?: Color.WHITE
    val SAFFRON: Color = colorMap["SAFFRON"] ?: Color.WHITE
    val SAGE_GREEN: Color = colorMap["SAGE_GREEN"] ?: Color.WHITE
    val SALMON_2: Color = colorMap["SALMON_2"] ?: Color.WHITE
    val SCRIBE_INK: Color = colorMap["SCRIBE_INK"] ?: Color.WHITE
    val SEAFOAM: Color = colorMap["SEAFOAM"] ?: Color.WHITE
    val SEAL_BROWN: Color = colorMap["SEAL_BROWN"] ?: Color.WHITE
    val SEAWATER: Color = colorMap["SEAWATER"] ?: Color.WHITE
    val SHADOW: Color = colorMap["SHADOW"] ?: Color.WHITE
    val SHAMROCK_GREEN: Color = colorMap["SHAMROCK_GREEN"] ?: Color.WHITE
    val SHARP_AZURE: Color = colorMap["SHARP_AZURE"] ?: Color.WHITE
    val SHINING_SKY: Color = colorMap["SHINING_SKY"] ?: Color.WHITE
    val SHINING_WHITE: Color = colorMap["SHINING_WHITE"] ?: Color.WHITE
    val SHRIMP: Color = colorMap["SHRIMP"] ?: Color.WHITE
    val SIENNA: Color = colorMap["SIENNA"] ?: Color.WHITE
    val SILVER: Color = colorMap["SILVER"] ?: Color.WHITE
    val SILVER_GREEN: Color = colorMap["SILVER_GREEN"] ?: Color.WHITE
    val SILVER_PINK: Color = colorMap["SILVER_PINK"] ?: Color.WHITE
    val SLATE_GRAY: Color = colorMap["SLATE_GRAY"] ?: Color.WHITE
    val SLOW_CREEK: Color = colorMap["SLOW_CREEK"] ?: Color.WHITE
    val SMOG: Color = colorMap["SMOG"] ?: Color.WHITE
    val SMOKE: Color = colorMap["SMOKE"] ?: Color.WHITE
    val SOAP: Color = colorMap["SOAP"] ?: Color.WHITE
    val SOFT_TEAL: Color = colorMap["SOFT_TEAL"] ?: Color.WHITE
    val SPACE_BLUE: Color = colorMap["SPACE_BLUE"] ?: Color.WHITE
    val SPEARMINT: Color = colorMap["SPEARMINT"] ?: Color.WHITE
    val SPRING_GREEN: Color = colorMap["SPRING_GREEN"] ?: Color.WHITE
    val STEAM: Color = colorMap["STEAM"] ?: Color.WHITE
    val STRAW: Color = colorMap["STRAW"] ?: Color.WHITE
    val STRONG_CYAN: Color = colorMap["STRONG_CYAN"] ?: Color.WHITE
    val STYGIAN_BLUE: Color = colorMap["STYGIAN_BLUE"] ?: Color.WHITE
    val SUBTLETY: Color = colorMap["SUBTLETY"] ?: Color.WHITE
    val SUDS: Color = colorMap["SUDS"] ?: Color.WHITE
    val TAN_2: Color = colorMap["TAN_2"] ?: Color.WHITE
    val TARNISH: Color = colorMap["TARNISH"] ?: Color.WHITE
    val TAUPE: Color = colorMap["TAUPE"] ?: Color.WHITE
    val TAXICAB_YELLOW: Color = colorMap["TAXICAB_YELLOW"] ?: Color.WHITE
    val TEA_ROSE: Color = colorMap["TEA_ROSE"] ?: Color.WHITE
    val THICK_AMETHYST: Color = colorMap["THICK_AMETHYST"] ?: Color.WHITE
    val THIN_AMETHYST: Color = colorMap["THIN_AMETHYST"] ?: Color.WHITE
    val THISTLE: Color = colorMap["THISTLE"] ?: Color.WHITE
    val THULIAN_PINK: Color = colorMap["THULIAN_PINK"] ?: Color.WHITE
    val TRANSPARENT: Color = colorMap["TRANSPARENT"] ?: Color.WHITE
    val TROPIC_MIST: Color = colorMap["TROPIC_MIST"] ?: Color.WHITE
    val TURQUOISE: Color = colorMap["TURQUOISE"] ?: Color.WHITE
    val TWILIGHT_CLOUD: Color = colorMap["TWILIGHT_CLOUD"] ?: Color.WHITE
    val TYRIAN_PURPLE: Color = colorMap["TYRIAN_PURPLE"] ?: Color.WHITE
    val ULTRAMARINE: Color = colorMap["ULTRAMARINE"] ?: Color.WHITE
    val UMBER: Color = colorMap["UMBER"] ?: Color.WHITE
    val VAPOR: Color = colorMap["VAPOR"] ?: Color.WHITE
    val VARISCITE: Color = colorMap["VARISCITE"] ?: Color.WHITE
    val VARNISH: Color = colorMap["VARNISH"] ?: Color.WHITE
    val VIOLET_2: Color = colorMap["VIOLET_2"] ?: Color.WHITE
    val VIOLET_CUSHIONS: Color = colorMap["VIOLET_CUSHIONS"] ?: Color.WHITE
    val VIRIDIAN: Color = colorMap["VIRIDIAN"] ?: Color.WHITE
    val WATERCOLOR_BLACK: Color = colorMap["WATERCOLOR_BLACK"] ?: Color.WHITE
    val WATERCOLOR_GRAY: Color = colorMap["WATERCOLOR_GRAY"] ?: Color.WHITE
    val WET_STONE: Color = colorMap["WET_STONE"] ?: Color.WHITE
    val WISTERIA: Color = colorMap["WISTERIA"] ?: Color.WHITE
    val WOODLANDS: Color = colorMap["WOODLANDS"] ?: Color.WHITE
    val ZUCCHINI: Color = colorMap["ZUCCHINI"] ?: Color.WHITE
}