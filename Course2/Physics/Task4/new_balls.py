import pygame
import sys
import math
import random

pygame.init()

WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Статические мячи")

BG_COLOR = (255, 255, 255)
PROJECTILE_COLOR = (255, 50, 50)
TARGET_COLOR = (50, 50, 255)

class MovingCircle:
    def __init__(self, x, y, radius, mass, vel_x=0, vel_y=0, color=(0, 0, 0)):
        self.pos = pygame.math.Vector2(x, y)
        self.vel = pygame.math.Vector2(vel_x, vel_y)
        self.radius = radius
        self.mass = mass
        self.color = color

    def update_position(self):
        self.pos += self.vel
        self.bounce_if_needed()

    def bounce_if_needed(self):
        if self.pos.x - self.radius < 0:
            self.pos.x = self.radius
            self.vel.x *= -1
        elif self.pos.x + self.radius > WIDTH:
            self.pos.x = WIDTH - self.radius
            self.vel.x *= -1
        if self.pos.y - self.radius < 0:
            self.pos.y = self.radius
            self.vel.y *= -1
        elif self.pos.y + self.radius > HEIGHT:
            self.pos.y = HEIGHT - self.radius
            self.vel.y *= -1

    def render(self, surface):
        pygame.draw.circle(surface, self.color, (int(self.pos.x), int(self.pos.y)), self.radius)

def handle_collision(projectile, target):
    delta = target.pos - projectile.pos
    dist = delta.length()
    if dist == 0 or dist > projectile.radius + target.radius:
        return False  # Столкновения нет

    normal = delta.normalize()
    relative_velocity = projectile.vel
    speed_along_normal = relative_velocity.dot(normal)
    if speed_along_normal <= 0:
        return False

    projectile.vel -= 2 * speed_along_normal * normal

    def randomize_angle(vel, max_degrees=10):
        angle = math.atan2(vel.y, vel.x)
        angle += math.radians(random.uniform(-max_degrees, max_degrees))
        speed = vel.length()
        return pygame.math.Vector2(math.cos(angle), math.sin(angle)) * speed

    projectile.vel = randomize_angle(projectile.vel)

    angle_deg = math.degrees(math.atan2(projectile.vel.y, projectile.vel.x))
    print(f"После столкновения: скорость = ({projectile.vel.x:.2f}, {projectile.vel.y:.2f}), угол = {angle_deg:.1f}°")

    return True

projectile = MovingCircle(100, HEIGHT // 2, radius=40, mass=10, vel_x=5, vel_y=0, color=PROJECTILE_COLOR)

targets = [
    MovingCircle(400, 250, radius=40, mass=1000, color=TARGET_COLOR),
    MovingCircle(600, 350, radius=40, mass=1000, color=TARGET_COLOR),
    MovingCircle(700, 150, radius=40, mass=1000, color=TARGET_COLOR),
    MovingCircle(300, 250, radius=40, mass=1000, color=TARGET_COLOR),
    MovingCircle(200, 350, radius=40, mass=1000, color=TARGET_COLOR),
    MovingCircle(100, 150, radius=40, mass=1000, color=TARGET_COLOR)
]

clock = pygame.time.Clock()
running = True

def update_screen():
    screen.fill(BG_COLOR)
    projectile.render(screen)
    for target in targets:
        target.render(screen)
    pygame.display.update()

while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    projectile.update_position()

    for target in targets[:]:
        if handle_collision(projectile, target):
            targets.remove(target)
            break

    update_screen()
    clock.tick(60)

pygame.quit()
sys.exit()
