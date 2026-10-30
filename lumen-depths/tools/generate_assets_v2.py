#!/usr/bin/env python3
"""Generates v2 textures: new items, blocks, and 64x64 entity sheets (UV-mapped to simple box models)."""
from PIL import Image, ImageDraw
import pathlib

BASE = pathlib.Path(__file__).resolve().parent.parent / "src/main/resources/assets/lumen_depths"
ITEM = BASE / "textures/item"; BLOCK = BASE / "textures/block"; ENT = BASE / "textures/entity"
for d in (ITEM, BLOCK, ENT): d.mkdir(parents=True, exist_ok=True)

def img(size):
    im = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    return im, ImageDraw.Draw(im)

def noise(d, size, base, var):
    import random; random.seed(42)
    for x in range(size):
        for y in range(size):
            v = random.randint(-var, var)
            d.point((x, y), fill=(max(0,min(255,base[0]+v)), max(0,min(255,base[1]+v)), max(0,min(255,base[2]+v)), base[3]))

# ---------- item textures 16x16 ----------
im, d = img(16)  # aurora_gem: magenta/pink diamond gem
d.polygon([(8,1),(13,7),(8,14),(3,7)], fill=(220,90,220,255))
d.polygon([(8,1),(13,7),(8,7)], fill=(255,170,255,255))
d.polygon([(3,7),(8,7),(8,14)], fill=(150,50,190,255))
d.line([(8,1),(8,14)], fill=(255,230,255,255))
im.save(ITEM/"aurora_gem.png")

im, d = img(16)  # crystal_dust: scattered cyan sparkles
for x,y,c in [(4,6,(140,240,255,255)),(8,4,(180,250,255,255)),(11,8,(120,220,240,255)),(6,11,(160,240,255,255)),(10,12,(200,255,255,255)),(8,8,(255,255,255,255)),(3,9,(100,200,230,255)),(12,4,(100,200,230,255))]:
    d.rectangle([x,y,x+1,y+1], fill=c)
im.save(ITEM/"crystal_dust.png")

im, d = img(16)  # gloom_fang: dark curved fang
d.polygon([(5,2),(10,4),(9,12),(7,14),(5,8)], fill=(60,40,80,255))
d.polygon([(5,2),(10,4),(8,7)], fill=(120,90,150,255))
d.line([(6,3),(8,13)], fill=(200,160,255,255))
im.save(ITEM/"gloom_fang.png")

# ---------- block textures 16x16 ----------
def ore(base_stone, spot_colors, seed=1):
    im, d = img(16); noise(d, 16, base_stone, 12)
    import random; random.seed(seed)
    for _ in range(6):
        x, y = random.randint(1,12), random.randint(1,12)
        c = spot_colors[x % len(spot_colors)]
        d.rectangle([x,y,x+2,y+2], fill=c)
        d.point((x+1,y), fill=(255,255,255,220))
    return im

stone=(128,128,128,255); deep=(50,50,56,255); cyan=(60,210,220,255); pink=(220,90,220,255)
ore(stone,(cyan,),1).save(BLOCK/"lumen_ore.png")
ore(deep,(cyan,),2).save(BLOCK/"deepslate_lumen_ore.png")
ore(deep,(pink,cyan),3).save(BLOCK/"aurora_ore.png")

im, d = img(16); noise(d,16,(40,150,170,255),18)   # lumen_crystal_block
for x in range(0,16,4): d.line([(x,0),(x,15)], fill=(90,220,240,255)); d.line([(0,x),(15,x)], fill=(20,90,110,255))
d.rectangle([5,5,10,10], fill=(150,250,255,255))
im.save(BLOCK/"lumen_crystal_block.png")

im, d = img(16); noise(d,16,(140,50,170,255),18)   # aurora_crystal_block
for x in range(0,16,4): d.line([(x,0),(x,15)], fill=(230,120,255,255)); d.line([(0,x),(15,x)], fill=(80,20,110,255))
d.rectangle([5,5,10,10], fill=(255,190,255,255))
im.save(BLOCK/"aurora_crystal_block.png")

im, d = img(16)  # lumen_crystal_cluster: bright shards on dark bg
d.rectangle([0,0,15,15], fill=(15,25,45,255))
for poly,c in [([(2,15),(5,4),(7,15)],(90,220,240,255)), ([(7,15),(10,1),(13,15)],(150,250,255,255)), ([(12,15),(14,7),(15,15)],(60,160,200,255))]:
    d.polygon(poly, fill=c)
d.line([(10,1),(10,15)], fill=(230,255,255,255))
im.save(BLOCK/"lumen_crystal_cluster.png")

im, d = img(16); noise(d,16,(70,110,150,255),10)   # crystal_bricks
for y in (0,7,15): d.line([(0,y),(15,y)], fill=(30,50,80,255))
d.line([(7,0),(7,7)], fill=(30,50,80,255)); d.line([(3,7),(3,15)], fill=(30,50,80,255)); d.line([(11,7),(11,15)], fill=(30,50,80,255))
for x,y in [(4,3),(12,10)]: d.point((x,y), fill=(160,220,255,255))
im.save(BLOCK/"crystal_bricks.png")

im, d = img(16)  # lumen_lantern sheet: body strip uv(0,4)-(16,12)
d.rectangle([0,0,15,15], fill=(70,70,80,255))
d.rectangle([0,4,15,12], fill=(90,90,100,255))       # body band
d.rectangle([2,6,13,10], fill=(120,240,250,255))     # glowing glass core
d.rectangle([4,7,11,9], fill=(200,255,255,255))
d.line([(0,4),(15,4)], fill=(40,40,50,255)); d.line([(0,12),(15,12)], fill=(40,40,50,255))
d.rectangle([8,0,9,3], fill=(60,60,70,255))          # handle/ring area
d.rectangle([8,13,9,15], fill=(60,60,70,255))
im.save(BLOCK/"lumen_lantern.png")

# ---------- entity textures 64x64 (mapped to model UVs) ----------
def box_sheet(fill_map, bg=(0,0,0,0)):
    im = Image.new("RGBA",(64,64),bg); d=ImageDraw.Draw(im)
    for rect,color in fill_map: d.rectangle(rect, fill=color)
    return im

CY=(77,238,234,255); WHT=(235,255,255,255); VIO=(150,110,220,255); MBODY=(90,70,140,255)
# glowmoth & sprite: bodies/wings/antennae boxes
glow = [((0,0,24,16),CY),((26,0,42,8),WHT),((26,10,42,18),VIO),((46,0,54,16),MBODY),((0,18,12,22),WHT),((14,18,26,22),WHT)]
box_sheet(glow).save(ENT/"glowmoth.png")
sprite = [((0,0,24,16),(120,240,255,255)),((26,0,42,8),(255,255,255,240)),((26,10,42,18),(180,255,255,240)),((46,0,54,16),(200,250,255,255)),((0,18,12,22),(255,255,255,220)),((14,18,26,22),(255,255,255,220))]
box_sheet(sprite).save(ENT/"lumen_sprite.png")

# snail: foot/head/horn/shell/spike regions purple body + blue shell
snail = [((0,0,12,16),(123,104,238,255)),((0,16,8,24),(123,104,238,255)),((40,8,42,16),(123,104,238,255)),
         ((16,16,48,48),(90,140,230,255)),((20,32,32,40),(160,210,255,255)),((0,0,64,64),(0,0,0,0))]
im = box_sheet([((0,0,12,16),(123,104,238,255)),((0,16,8,24),(140,120,245,255)),((40,8,42,16),(123,104,238,255)),
                ((16,16,48,48),(90,140,230,255))])
d = ImageDraw.Draw(im)
for i in range(16,48,8):
    d.line([(i,16),(i,48)], fill=(160,210,255,255)); d.line([(16,i),(48,i)], fill=(50,80,170,255))
d.rectangle([20,32,32,40], fill=(170,220,255,255))
d.point((2,19), fill=(20,20,40,255)); d.point((5,19), fill=(20,20,40,255))
im.save(ENT/"crystal_snail.png")

# beetle: low body + legs + shell
box_sheet([((0,0,20,12),(60,90,200,255)),((22,0,34,8),(138,111,232,255)),((0,14,20,20),(90,140,230,255)),
           ((44,0,48,24),(40,40,70,255)),((52,0,56,24),(40,40,70,255))]).save(ENT/"crystal_beetle.png")

# wraith: dark shards + cyan eyes
box_sheet([((0,0,20,32),(26,26,62,255)),((22,0,38,8),(74,238,234,255)),((22,10,38,26),(40,40,90,255)),
           ((44,0,52,20),(60,60,120,255)),((0,34,10,40),(74,238,234,255))]).save(ENT/"shard_wraith.png")

# maw: dark body, glowing mouth region
box_sheet([((0,0,24,16),(43,27,61,255)),((26,0,42,10),(70,40,90,255)),((26,12,42,22),(217,79,255,255)),
           ((46,0,54,14),(55,35,75,255)),((0,18,8,22),(255,255,180,255)),((10,18,18,22),(255,255,180,255))]).save(ENT/"gloom_maw.png")

print("v2 assets generated OK")
