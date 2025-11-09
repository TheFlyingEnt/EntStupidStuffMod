#version 150

uniform sampler2D In;
uniform float ShiftAmount;
uniform float Time;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    vec2 offset = vec2(ShiftAmount * sin(Time * 2.0), ShiftAmount * cos(Time * 2.0));

    float r = texture(In, texCoord + offset).r;
    float g = texture(In, texCoord).g;
    float b = texture(In, texCoord - offset).b;

    fragColor = vec4(r, g, b, 1.0);
}
