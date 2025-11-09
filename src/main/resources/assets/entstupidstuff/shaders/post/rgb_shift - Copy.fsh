#version 150

uniform sampler2D InSampler;
uniform float Time;

in vec2 texCoord;

out vec4 fragColor;

// Uniforms from the post-processor config
uniform float ShiftAmount;
uniform float RotationSpeed;

void main() {
    // Calculate RGB shift offsets based on time
    float angle = Time * RotationSpeed;
    
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
    
    // Sample each color channel with its respective offset
    float r = texture(InSampler, texCoord + redOffset).r;
    float g = texture(InSampler, texCoord + greenOffset).g;
    float b = texture(InSampler, texCoord + blueOffset).b;
    
    // Combine the separated channels
    fragColor = vec4(r, g, b, 1.0);
}