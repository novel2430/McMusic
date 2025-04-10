{
  description = "Mc Fabric Mod Development";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-24.11";
  };

  outputs = { self, nixpkgs }:
  
  let 
    pkgs = nixpkgs.legacyPackages.x86_64-linux;
    libs = with pkgs; [
        libpulseaudio
        libGL
        glfw
        openal
        stdenv.cc.cc.lib
        jdk21 #21
      ];
  in {
    devShell.x86_64-linux = pkgs.mkShell {
      packages = [];
      buildInputs = libs;
      LD_LIBRARY_PATH = pkgs.lib.makeLibraryPath libs;
    };
  };
}
