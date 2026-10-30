#!/usr/bin/env python3
"""Procedurally generates all original PNG textures for the Lumen Depths mod."""
from PIL import Image, ImageDraw
import pathlib

BASE = pathlib.Path(__file__).resolve().parent.parent / "src/main/resources/assets/lumen_depths"
ITEM_DIR = BASE / "textures/item"
ENT_DIR = BASE / "textures/entity"
ITEM_DIR.mkdir(parents=True, exist_ok=True)
ENT_DIR.mkdir(parents=True, exist_ok=True)


def px(size):
    img = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    return img, ImageDraw.Draw(img)


# ---- lumen_shard.png: cyan crystal shard on transparent bg ----
img, d = px(16)
d.polygon([(8, 1), (11, 6), (9, 14), (5, 12), (4, 5)], fill=(64, 224, 230, 255))
d.polygon([(8, 1), (11, 6), (8, 8)], fill=(150, 250, 255, 255))
d.polygon([(4, 5), (8, 8), (5, 12)], fill=(30, 144, 190, 255))
d.line([(8, 1), (8, 8), (9, 14)], fill=(220, 255, 255, 255), width=1)
for x, y in [(7, 3), (6, 7), (10, 5)]:
    d.point((x, y), fill=(255, 255, 255, 200))
img.save(ITEM_DIR / "lumen_shard.png")

# ---- wisp_essence.png: glowing golden orb ----
img, d = px(16)
for r, c in [(6, (255, 180, 40, 90)), (5, (255, 200, 60, 160)), (4, (255, 220, 90, 255)), (3, (255, 240, 160, 255))]:
    d.ellipse([8 - r, 8 - r, 8 + r, 8 + r], fill=c)
d.ellipse([6, 5, 9, 8], fill=(255, 255, 220, 255))
img.save(ITEM_DIR / "wisp_essence.png")

# ---- aurora_fruit.png: purple-pink fruit with green leaf ----
img, d = px(16)
d.ellipse([3, 5, 12, 14], fill=(160, 60, 180, 255))
d.ellipse([4, 6, 11, 13], fill=(220, 90, 170, 255))
d.ellipse([5, 7, 8, 10], fill=(250, 150, 200, 255))
d.polygon([(8, 5), (12, 2), (13, 4), (9, 6)], fill=(60, 170, 70, 255))
d.line([(8, 5), (8, 3)], fill=(110, 70, 40, 255), width=1)
img.save(ITEM_DIR / "aurora_fruit.png")

# ---- lumen_lantern.png: small lantern with teal light ----
img, d = px(16)
d.rectangle([4, 3, 11, 4], fill=(90, 70, 40, 255))          # top cap
d.rectangle([7, 1, 8, 3], fill=(90, 70, 40, 255))           # hanger
d.rectangle([4, 12, 11, 13], fill=(90, 70, 40, 255))        # bottom
d.rectangle([5, 4, 10, 12], fill=(40, 200, 200, 255))       # glass light
d.rectangle([6, 5, 9, 11], fill=(180, 255, 250, 255))       # inner glow
d.line([(5, 4), (5, 12)], fill=(90, 70, 40, 255))
d.line([(10, 4), (10, 12)], fill=(90, 70, 40, 255))
img.save(ITEM_DIR / "lumen_lantern.png")

# ---- glowmoth.png: 64x64 entity texture matching model UVs ----
# body uv(0,0) 3x2x6 ; head uv(0,8) 3x3x2 ; antennae uv(12,0); wings uv(12,4) 6x1x6
img, d = px(64)
body = (40, 30, 60, 255)
wing = (77, 238, 234, 255)      # cyan glow
wing2 = (179, 157, 219, 255)    # lavender accent
head_c = (60, 45, 85, 255)
# body net: unwrap 3x2x6 cuboid at (0,0): layout [right(2x6)? ] -- use simple block fills per face region
# Model box faces for w=3,h=2,d=6 at uv(0,0): row1: x.. etc. We just paint a pleasant gradient over the used area.
d.rectangle([0, 0, 16, 8], fill=body)            # body area approx
d.rectangle([0, 8, 6, 14], fill=head_c)          # head area
d.rectangle([12, 0, 15, 4], fill=wing2)          # antennae
d.rectangle([12, 4, 30, 12], fill=wing)          # wings area
for i in range(12, 30, 3):                       # wing stripes
    d.line([(i, 4), (i, 12)], fill=(255, 255, 255, 160))
d.rectangle([14, 6, 17, 9], fill=wing2)
d.rectangle([24, 6, 27, 9], fill=wing2)
img.save(ENT_DIR / "glowmoth.png")

# ---- crystal_snail.png: 64x64, purple body, blue crystal shell ----
img, d = px(64)
foot_c = (120, 80, 160, 255)     # purple body
shell_c = (90, 140, 230, 255)    # blue crystal
shell_hi = (160, 210, 255, 255)
d.rectangle([0, 0, 12, 16], fill=foot_c)         # foot net area
d.rectangle([0, 16, 8, 24], fill=foot_c)         # head net area
d.point((2, 19), fill=(20, 20, 40, 255))         # eye
d.point((5, 19), fill=(20, 20, 40, 255))         # eye
d.rectangle([16, 16, 48, 48], fill=shell_c)      # shell net area
for i in range(16, 48, 8):
    d.line([(i, 16), (i, 48)], fill=shell_hi)
    d.line([(16, i - 0), (48, i - 0)], fill=(50, 80, 170, 255))
d.rectangle([20, 32, 32, 40], fill=shell_hi)     # spike area highlight
d.rectangle([40, 8, 42, 16], fill=foot_c)        # horns area
img.save(ENT_DIR / "crystal_snail.png")

# ---- icon.png: 128x128 glowing blue-violet crystal on dark bg ----
img = Image.new("RGBA", (128, 128), (12, 10, 28, 255))
d = ImageDraw.Draw(img)
cx, cy = 64, 64
for r, c in [(56, (30, 20, 70, 255)), (46, (60, 40, 130, 120))]:
    d.ellipse([cx - r, cy - r, cx + r, cy + r], fill=c)
d.polygon([(64, 14), (92, 52), (78, 112), (50, 104), (36, 46)], fill=(90, 120, 240, 255))
d.polygon([(64, 14), (92, 52), (64, 62)], fill=(150, 180, 255, 255))
d.polygon([(36, 46), (64, 62), (50, 104)], fill=(120, 70, 200, 255))
d.polygon([(64, 62), (78, 112), (50, 104)], fill=(70, 60, 160, 255))
d.line([(64, 14), (64, 62), (64, 112)], fill=(220, 235, 255, 255), width=2)
for x, y in [(24, 24), (100, 30), (18, 90), (108, 96), (64, 120)]:
    d.ellipse([x - 2, y - 2, x + 2, y + 2], fill=(200, 220, 255, 220))
img.save(BASE / "icon.png")
print("assets generated OK")
