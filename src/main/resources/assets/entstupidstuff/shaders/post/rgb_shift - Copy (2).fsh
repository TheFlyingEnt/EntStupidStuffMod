#version 150

uniform sampler2D InSampler;
uniform float GameTime;

in vec2 texCoord;

out vec4 fragColor;

// Uniforms from the post-processor config
uniform float ShiftAmount;
uniform float RotationSpeed;

void main() {
    // Calculate RGB shift offsets based on game time
    // GameTime goes from 0-1 over 20 minutes, so multiply to get continuous animation
    float angle = GameTime * 1200.0 * RotationSpeed;
    
    // Create different offsets for each color channel in a circular pattern
    // Each channel is offset by 120 degrees (2.094 radians)
    vec2 redOffset = vec2(
        cos(angle) * ShiftAmount,
        sin(angle) * ShiftAmount
    );
    
    vec2 greenOffset = vec2(
        cos(angle + 2.094395) * ShiftAmount,
        sin(angle + 2.094395) * ShiftAmount
    );
    
    vec2 blueOffset = vec2(
        cos(angle + 4.188790) * ShiftAmount,
        sin(angle + 4.188790) * ShiftAmount
    );
    
    // Clamp texture coordinates to prevent black edges
    vec2 redCoord = clamp(texCoord + redOffset, 0.0, 1.0);
    vec2 greenCoord = clamp(texCoord + greenOffset, 0.0, 1.0);
    vec2 blueCoord = clamp(texCoord + blueOffset, 0.0, 1.0);
    
    // Sample each color channel with its respective offset
    float r = texture(InSampler, redCoord).r;
    float g = texture(InSampler, greenCoord).g;
    float b = texture(InSampler, blueCoord).b;
    
    // Combine the separated channels
    fragColor = vec4(r, g, b, 1.0);
}