#!/usr/bin/env python3
"""Writes all static data JSONs (models, blockstates, loot, tags, worldgen) and lang files for Lumen Depths v2."""
import json, pathlib

R = pathlib.Path(__file__).resolve().parent.parent / "src/main/resources"
A = R / "assets/lumen_depths"
D = R / "data/lumen_depths"
M = "lumen_depths"


def w(path, obj):
    path.parent.mkdir(parents=True, exist_ok=True)
    if isinstance(obj, str):
        path.write_text(obj)
    else:
        path.write_text(json.dumps(obj, indent=2))


# ---------- item models (generated) ----------
for it in ["lumen_shard", "wisp_essence", "aurora_fruit", "aurora_gem", "crystal_dust", "gloom_fang"]:
    w(A / f"models/item/{it}.json", {"parent": "minecraft:item/generated",
                                     "textures": {"layer0": f"{M}:item/{it}"}})

# ---------- spawn egg models ----------
for e in ["glowmoth", "crystal_snail", "lumen_sprite", "crystal_beetle", "shard_wraith", "gloom_maw"]:
    w(A / f"models/item/{e}_spawn_egg.json", {"parent": "minecraft:item/template_spawn_egg"})

# ---------- block models ----------
for b in ["lumen_ore", "deepslate_lumen_ore", "aurora_ore", "lumen_crystal_block",
          "aurora_crystal_block", "crystal_bricks"]:
    w(A / f"models/block/{b}.json", {"parent": "minecraft:block/cube_all",
                                     "textures": {"all": f"{M}:block/{b}"}})
    w(A / f"models/item/{b}.json", {"parent": f"{M}:block/{b}"})

w(A / "models/block/lumen_crystal_cluster.json", {
    "credit": "original model, generated procedurally",
    "textures": {"particle": f"{M}:block/lumen_crystal_cluster", "crystal": f"{M}:block/lumen_crystal_cluster"},
    "elements": [
        {"from": [4, 0, 4], "to": [7, 8, 7], "faces": {
            "north": {"uv": [0, 8, 3, 16], "texture": "#crystal"}, "east": {"uv": [0, 8, 3, 16], "texture": "#crystal"},
            "south": {"uv": [0, 8, 3, 16], "texture": "#crystal"}, "west": {"uv": [0, 8, 3, 16], "texture": "#crystal"},
            "up": {"uv": [0, 5, 3, 8], "texture": "#crystal"}, "down": {"uv": [0, 5, 3, 8], "texture": "#crystal"}}},
        {"from": [8, 0, 7], "to": [12, 12, 11], "faces": {
            "north": {"uv": [4, 4, 8, 16], "texture": "#crystal"}, "east": {"uv": [4, 4, 8, 16], "texture": "#crystal"},
            "south": {"uv": [4, 4, 8, 16], "texture": "#crystal"}, "west": {"uv": [4, 4, 8, 16], "texture": "#crystal"},
            "up": {"uv": [4, 0, 8, 4], "texture": "#crystal"}, "down": {"uv": [4, 0, 8, 4], "texture": "#crystal"}}},
        {"from": [3, 0, 9], "to": [6, 5, 12], "faces": {
            "north": {"uv": [10, 11, 13, 16], "texture": "#crystal"}, "east": {"uv": [10, 11, 13, 16], "texture": "#crystal"},
            "south": {"uv": [10, 11, 13, 16], "texture": "#crystal"}, "west": {"uv": [10, 11, 13, 16], "texture": "#crystal"},
            "up": {"uv": [10, 8, 13, 11], "texture": "#crystal"}, "down": {"uv": [10, 8, 13, 11], "texture": "#crystal"}}}
    ]})
w(A / "models/item/lumen_crystal_cluster.json", {"parent": f"{M}:block/lumen_crystal_cluster"})

# lantern models (own geometry, own texture)
def lantern_model(hanging):
    if hanging:
        parts = [([5, 5, 5], [11, 13, 11], [0, 4, 16, 12]),   # body
                 ([7, 13, 7], [9, 15, 9], [8, 0, 10, 2]),     # top ring
                 ([6, 4, 6], [10, 5, 10], [8, 2, 12, 3]),     # bottom ring
                 ([7, 0, 7], [9, 4, 9], [0, 0, 2, 4])]        # chain/handle below ceiling
    else:
        parts = [([5, 2, 5], [11, 10, 11], [0, 4, 16, 12]),   # body
                 ([6, 10, 6], [10, 11, 10], [8, 0, 12, 1]),   # top ring
                 ([5, 1, 5], [11, 2, 11], [8, 2, 14, 3]),     # base plate
                 ([7, 11, 7], [9, 14, 9], [0, 0, 2, 3])]      # handle above
    els = []
    for frm, to, uv in parts:
        x0, y0, z0 = frm; x1, y1, z1 = to
        u0, v0, u1, v1 = uv
        faces = {}
        for dir_ in ("north", "south", "east", "west"):
            faces[dir_] = {"uv": [u0, v0, u1, v1], "texture": "#lantern"}
        faces["up"] = {"uv": [u0, v0, u1, v1], "texture": "#lantern"}
        faces["down"] = {"uv": [u0, v0, u1, v1], "texture": "#lantern"}
        els.append({"from": list(frm), "to": list(to), "faces": faces})
    return {"credit": "original procedural model", "ambientocclusion": False,
            "textures": {"particle": "lumen_depths:block/lumen_lantern",
                         "lantern": "lumen_depths:block/lumen_lantern"},
            "elements": els}

w(A / "models/block/lumen_lantern.json", lantern_model(False))
w(A / "models/block/lumen_lantern_hanging.json", lantern_model(True))
w(A / "models/item/lumen_lantern.json", {"parent": f"{M}:block/lumen_lantern"})

w(A / "blockstates/lumen_lantern.json", {
    "variants": {
        "hanging=false": {"model": f"{M}:block/lumen_lantern"},
        "hanging=true": {"model": f"{M}:block/lumen_lantern_hanging"}}})

for b in ["lumen_ore", "deepslate_lumen_ore", "aurora_ore", "lumen_crystal_block",
          "aurora_crystal_block", "lumen_crystal_cluster", "crystal_bricks"]:
    w(A / f"blockstates/{b}.json", {"variants": {"": {"model": f"{M}:block/{b}"}}})

# ---------- loot tables ----------
def drop_item(item, cnt="1"):
    return {"type": "minecraft:item", "name": item, "functions": [{"function": "minecraft:set_count", "count": int(cnt)}] if cnt != "1" else []}

def ore_loot(block, drop, silk_extra=None):
    funcs = [{"function": "minecraft:apply_bonus", "formula": "minecraft:uniform", "enchantment": "minecraft:fortune",
              "operations": [{"bonus": "binomial_addition", "add": 1.0, "n": 1.0, "p": 0.88}]}]
    entry = {"type": "minecraft:item", "name": drop, "functions": funcs}
    pools = [{"rolls": 1.0, "entries": [entry]}]
    if silk_extra:
        pools[0]["conditions"] = [{"condition": "minecraft:inverted",
                                   "term": {"condition": "minecraft:match_tool",
                                            "predicate": {"enchantments": [{"enchantment": "minecraft:silk_touch",
                                                                            "levels": {"min": 1}}]}}}]
        pools.append({"rolls": 1.0, "entries": [{"type": "minecraft:item", "name": silk_extra}],
                      "conditions": [{"condition": "minecraft:match_tool",
                                      "predicate": {"enchantments": [{"enchantment": "minecraft:silk_touch",
                                                                      "levels": {"min": 1}}]}}]})
    return {"type": "minecraft:block", "pools": pools}

w(D / "loot_table/blocks/lumen_ore.json", ore_loot("lumen_ore", "lumen_depths:lumen_shard", "lumen_depths:lumen_ore"))
w(D / "loot_table/blocks/deepslate_lumen_ore.json", ore_loot("deepslate_lumen_ore", "lumen_depths:lumen_shard", "lumen_depths:deepslate_lumen_ore"))
w(D / "loot_table/blocks/aurora_ore.json", ore_loot("aurora_ore", "lumen_depths:aurora_gem", "lumen_depths:aurora_ore"))
for b in ["lumen_crystal_block", "aurora_crystal_block", "crystal_bricks", "lumen_crystal_cluster", "lumen_lantern"]:
    w(D / f"loot_table/blocks/{b}.json", {"type": "minecraft:block", "pools": [{"rolls": 1.0, "entries": [
        {"type": "minecraft:item", "name": f"{M}:{b}"}]}]})

# entity loot tables (used by /loot and as documentation; entities also drop in code)
ENT_LOOT = {
    "glowmoth": [("lumen_depths:wisp_essence", "1-2"), ("lumen_depths:lumen_shard", "25%")],
    "crystal_snail": [("lumen_depths:lumen_shard", "1-2"), ("lumen_depths:wisp_essence", "20%")],
    "lumen_sprite": [("lumen_depths:wisp_essence", "1-2"), ("lumen_depths:crystal_dust", "25%")],
    "crystal_beetle": [("lumen_depths:crystal_dust", "1-2"), ("lumen_depths:lumen_shard", "25%")],
    "shard_wraith": [("lumen_depths:lumen_shard", "1-2"), ("lumen_depths:wisp_essence", "50%"), ("lumen_depths:aurora_gem", "10%")],
    "gloom_maw": [("lumen_depths:gloom_fang", "1-2"), ("lumen_depths:lumen_shard", "50%")]
}
def parse(spec):
    if spec.endswith("%"):
        p = float(spec[:-1]) / 100.0
        return {"type": "minecraft:item", "name": "", "conditions": [{"condition": "minecraft:random_chance", "chance": p}]}
    if "-" in spec:
        lo, hi = spec.split("-")
        return {"type": "minecraft:item", "name": "", "functions": [
            {"function": "minecraft:set_count", "count": {"type": "minecraft:uniform", "min": int(lo), "max": int(hi)}}]}
    return {"type": "minecraft:item", "name": ""}
for ent, drops in ENT_LOOT.items():
    entries = []
    for name, spec in drops:
        e = parse(spec); e["name"] = name; entries.append(e)
    w(D / f"loot_table/entities/{ent}.json", {"type": "minecraft:entity", "pools": [{"rolls": 1.0, "entries": entries}]})

# ---------- tags ----------
mineable = {"values": [f"{M}:{b}" for b in ["lumen_ore", "deepslate_lumen_ore", "aurora_ore",
                                            "lumen_crystal_block", "aurora_crystal_block",
                                            "crystal_bricks", "lumen_lantern"]]}
w(R / "data/minecraft/tags/block/mineable/pickaxe.json", mineable)
w(R / "data/minecraft/tags/block/needs_stone_tool.json", {"replace": False, "values": [
    f"{M}:lumen_ore", f"{M}:deepslate_lumen_ore", f"{M}:aurora_ore", f"{M}:lumen_crystal_block",
    f"{M}:aurora_crystal_block", f"{M}:crystal_bricks", f"{M}:lumen_lantern"]})
w(D / "tags/block/lumen_caves_replaceable.json", {"values": ["minecraft:stone", "minecraft:cobblestone",
                                                             "minecraft:deepslate", "minecraft:tuff", "minecraft:dripstone_block"]})

# ---------- worldgen ----------
def ore_config(targets_key, count, size, states):
    return {"type": "minecraft:ore",
            "config": {
                "size": size,
                "discard_chance_on_air_exposure": 0.0,
                "targets": [{"target": {"predicate_type": "minecraft:block_matcher", "block": t[0]},
                             "state": {"Name": t[1]}} for t in states]}}

w(D / "worldgen/configured_feature/lumen_ore_upper.json",
  ore_config("", 0, 7, [("stone", "lumen_depths:lumen_ore"), ("infested_stone", "lumen_depths:lumen_ore")]))
w(D / "worldgen/configured_feature/lumen_ore_lower.json",
  ore_config("", 0, 9, [("deepslate", "lumen_depths:deepslate_lumen_ore")]))
w(D / "worldgen/configured_feature/aurora_ore_deep.json",
  ore_config("", 0, 4, [("deepslate", "lumen_depths:aurora_ore")]))

def placed(count, targets_key, height_min, height_range):
    return {"feature": targets_key,
            "placement": [
                {"type": "minecraft:count", "count": count},
                {"type": "minecraft:in_square"},
                {"type": "minecraft:height_range", "height": {
                    "type": "minecraft:trapezoid", "min_inclusive": {"absolute": height_min},
                    "max_inclusive": {"absolute": height_min + height_range}}},
                {"type": "minecraft:biome"}]}

w(D / "worldgen/placed_feature/lumen_ore_upper.json", placed(8, "lumen_depths:lumen_ore_upper", 0, 160))
w(D / "worldgen/placed_feature/lumen_ore_lower.json", placed(10, "lumen_depths:lumen_ore_lower", -64, 70))
w(D / "worldgen/placed_feature/aurora_ore_deep.json", placed(3, "lumen_depths:aurora_ore_deep", -64, 40))

# crystal cluster decoration feature (surface scatter like patch grass but our block)
w(D / "worldgen/configured_feature/lumen_crystal_cluster_patch.json", {
    "type": "minecraft:simple_block",
    "config": {"to_place": {"type": "minecraft:single_provider", "provider": {
        "type": "minecraft:single_state", "state": {"Name": "lumen_depths:lumen_crystal_cluster"}}}}})
w(D / "worldgen/placed_feature/lumen_crystal_cluster_patch.json", {
    "feature": "lumen_depths:lumen_crystal_cluster_patch",
    "placement": [
        {"type": "minecraft:count", "count": 4},
        {"type": "minecraft:in_square"},
        {"type": "minecraft:height_range", "height": {
            "type": "minecraft:uniform", "min_inclusive": {"absolute": -40}, "max_inclusive": {"absolute": 32}}},
        {"type": "minecraft:surface_water_depth_filter", "max_water_depth": 0},
        {"type": "minecraft:biome"}]})

# biome with cave carver, spawns, colors
w(D / "worldgen/biome/lumen_caves.json", {
    "temperature": 0.5, "downfall": 0.2, "has_precipitation": False,
    "effects": {
        "sky_color": 7972608, "fog_color": 4144838, "water_color": 3847116, "water_fog_color": 2694697,
        "grass_color": 5242867, "foliage_color": 7929370},
    "carvers": {"air": ["minecraft:cave", "minecraft:cave_z", "minecraft:canyon"]},
    "features": [[], [], ["lumen_depths:lumen_crystal_cluster_patch"], [], [],
                 ["lumen_depths:lumen_ore_upper", "lumen_depths:lumen_ore_lower", "lumen_depths:aurora_ore_deep"],
                 [], [], [], ["minecraft:lake_lava_underground", "minecraft:lake_lava_surface"], [], [],
                 ["minecraft:monster_room", "minecraft:monster_room_deep"], [], [], [], [], [], [],
                 ["minecraft:ore_dirt", "minecraft:ore_gravel", "minecraft:ore_granite_upper", "minecraft:ore_granite_lower",
                  "minecraft:ore_diorite_upper", "minecraft:ore_diorite_lower", "minecraft:ore_andesite_upper",
                  "minecraft:ore_andesite_lower", "minecraft:ore_tuff", "minecraft:ore_coal_upper", "minecraft:ore_coal_lower",
                  "minecraft:ore_iron_upper", "minecraft:ore_iron_middle", "minecraft:ore_iron_small",
                  "minecraft:ore_gold", "minecraft:ore_gold_lower", "minecraft:ore_redstone", "minecraft:ore_redstone_lower",
                  "minecraft:ore_diamond", "minecraft:ore_diamond_medium", "minecraft:ore_diamond_large",
                  "minecraft:ore_diamond_buried", "minecraft:ore_lapis", "minecraft:ore_lapis_buried",
                  "minecraft:ore_copper", "minecraft:underwater_magma", "minecraft:disk_sand", "minecraft:disk_clay",
                  "minecraft:disk_gravel"]],
    "spawners": {
        "monster": [{"type": "lumen_depths:shard_wraith", "weight": 30, "minCount": 1, "maxCount": 2},
                    {"type": "lumen_depths:gloom_maw", "weight": 40, "minCount": 1, "maxCount": 3}],
        "creature": [{"type": "lumen_depths:crystal_beetle", "weight": 30, "minCount": 2, "maxCount": 3},
                     {"type": "lumen_depths:crystal_snail", "weight": 20, "minCount": 2, "maxCount": 3}],
        "ambient": [{"type": "lumen_depths:glowmoth", "weight": 40, "minCount": 2, "maxCount": 4},
                    {"type": "lumen_depths:lumen_sprite", "weight": 25, "minCount": 1, "maxCount": 3}],
        "axolotls": [], "underground_water_creature": [], "water_creature": [], "water_ambient": [], "misc": []},
    "spawn_costs": {},
    "element_constructors": {}})

print("data written OK")
