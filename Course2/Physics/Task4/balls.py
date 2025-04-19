import pygame
import sys
import math

pygame.init()

WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Движущиеся шары")

BG_COLOR = (255, 255, 255)
COLOR_1 = (255, 50, 50)
COLOR_2 = (50, 50, 255)

class MovingCircle:
    def __init__(self, pos_x, pos_y, mass, vel_x, vel_y, radius, color):
        self.pos = pygame.math.Vector2(pos_x, pos_y)
        self.vel = pygame.math.Vector2(vel_x, vel_y)
        self.mass = mass
        self.radius = radius
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

def handle_collision(obj1, obj2):
    delta = obj2.pos - obj1.pos
    dist = delta.length()
    if dist == 0 or dist > obj1.radius + obj2.radius:
        return

    normal = delta.normalize()
    relative_velocity = obj2.vel - obj1.vel
    speed_along_normal = relative_velocity.dot(normal)
    if speed_along_normal > 0:
        return

    impulse_mag = (2 * speed_along_normal) / (obj1.mass + obj2.mass)
    obj1.vel += impulse_mag * obj2.mass * normal
    obj2.vel -= impulse_mag * obj1.mass * normal

    overlap = (obj1.radius + obj2.radius - dist) / 2
    obj1.pos -= normal * overlap
    obj2.pos += normal * overlap

    angle1 = math.degrees(math.atan2(obj1.vel.y, obj1.vel.x))
    angle2 = math.degrees(math.atan2(obj2.vel.y, obj2.vel.x))

    print(f"После столкновения:")
    print(f"  Красный шар: скорость = ({obj1.vel.x:.2f}, {obj1.vel.y:.2f}), угол = {angle1:.1f}°")
    print(f"  Синий шар: скорость = ({obj2.vel.x:.2f}, {obj2.vel.y:.2f}), угол = {angle2:.1f}°")

circle_a = MovingCircle(1, 280, 20, 10, 0, 50, COLOR_1)
circle_b = MovingCircle(799, 300, 20, 10, 0, 50, COLOR_2)

clock = pygame.time.Clock()
running = True

def update_screen():
    screen.fill(BG_COLOR)
    circle_a.render(screen)
    circle_b.render(screen)
    pygame.display.update()

while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    circle_a.update_position()
    circle_b.update_position()
    handle_collision(circle_a, circle_b)
    update_screen()
    clock.tick(60)

pygame.quit()
sys.exit()