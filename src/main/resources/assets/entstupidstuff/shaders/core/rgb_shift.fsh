#version 150

uniform sampler2D DiffuseSampler;
uniform float Time;

in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;

void main() {
    // Calculate RGB shift offsets based on time
    // Use smaller shift amount for subtler effect
    float shiftAmount = 0.003;
    float angle = Time * 3.0; // Speed of rotation
    
    // Create different offsets for each color channel in a circular pattern
    // Each channel is offset by 120 degrees (2.094 radians)
    vec2 redOffset = vec2(
        cos(angle) * shiftAmount,
        sin(angle) * shiftAmount
    );
    
    vec2 greenOffset = vec2(
        cos(angle + 2.094395) * shiftAmount,
        sin(angle + 2.094395) * shiftAmount
    );
    
    vec2 blueOffset = vec2(
        cos(angle + 4.188790) * shiftAmount,
        sin(angle + 4.188790) * shiftAmount
    );
    
    // Sample each color channel with its respective offset
    float r = texture(DiffuseSampler, texCoord + redOffset).r;
    float g = texture(DiffuseSampler, texCoord + greenOffset).g;
    float b = texture(DiffuseSampler, texCoord + blueOffset).b;
    
    // Combine the separated channels
    fragColor = vec4(r, g, b, 1.0);
}