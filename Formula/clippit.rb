class Clippit < Formula
  desc "Clippit, A simple tool to clip, save, and load your directories or files."
  homepage "https://github.com/Dwk0910/Clippit"
  url "https://github.com/Dwk0910/Clippit/releases/download/v1.0.0/Clippit.jar"
  sha256 "535af85b5ce95cc162dd07e9d05958d72c6ae3e0ebe8ba799c76b193d11c7325"

  depends_on "openjdk@17"

  def install
    libexec.install "Clippit.jar" => "clippit.jar"
    (bin/"cl").write <<~EOS
      #!/bin/bash
      exec "#{Formula["openjdk@17"].opt_bin}/java" -jar "#{libexec}/clippit.jar" "$@"
    EOS
  end

  test do
    assert_match "Clippit", shell_output("#{bin}/cl help")
  end
end