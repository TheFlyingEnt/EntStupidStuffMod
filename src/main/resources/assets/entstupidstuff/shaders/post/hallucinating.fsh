#version 150

uniform sampler2D DiffuseSampler;
uniform float Time;

in vec2 texCoord;
out vec4 fragColor;

void main() {
    float wave = sin(Time * 2.0 + texCoord.y * 20.0) * 0.01;

    vec2 rUV = texCoord + vec2(wave, 0.0);
    vec2 gUV = texCoord;
    vec2 bUV = texCoord - vec2(wave, 0.0);

    float r = texture(DiffuseSampler, rUV).r;
    float g = texture(DiffuseSampler, gUV).g;
    float b = texture(DiffuseSampler, bUV).b;

    fragColor = vec4(r, g, b, 1.0);
}
