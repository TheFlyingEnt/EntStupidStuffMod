#version 150

uniform sampler2D InSampler;
uniform float GameTime;

in vec2 texCoord;

out vec4 fragColor;

// Uniforms from the post-processor config
uniform float ShiftAmount;
uniform float RotationSpeed;

void main() {
    // Calculate direction from center for radial chromatic aberration
    vec2 centerOffset = texCoord - 0.5;
    float dist = length(centerOffset);
    vec2 direction = normalize(centerOffset);
    
    // Add some animation/wobble based on game time
    float wobble = sin(GameTime * 1200.0 * RotationSpeed) * 0.5 + 0.5;
    float animatedShift = ShiftAmount * (1.0 + wobble * 0.5);
    
    // Shift each color channel outward from center at different amounts
    vec2 redCoord = texCoord + direction * animatedShift * 1.5;
    vec2 greenCoord = texCoord; // Green stays in center
    vec2 blueCoord = texCoord - direction * animatedShift * 1.5;
    
    // Clamp texture coordinates to prevent black edges
    redCoord = clamp(redCoord, 0.0, 1.0);
    greenCoord = clamp(greenCoord, 0.0, 1.0);
    blueCoord = clamp(blueCoord, 0.0, 1.0);
    
    // Sample each color channel
    float r = texture(InSampler, redCoord).r;
    float g = texture(InSampler, greenCoord).g;
    float b = texture(InSampler, blueCoord).b;
    
    // Combine the separated channels
    fragColor = vec4(r, g, b, 1.0);
}