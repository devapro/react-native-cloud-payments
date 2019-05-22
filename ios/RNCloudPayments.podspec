
Pod::Spec.new do |s|
  s.name         = "RNCloudPayments"
  s.version      = "1.0.0"
  s.summary      = "RNCloudPayments"
  s.description  = <<-DESC
                  RNCloudPayments
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNCloudPayments.git", :tag => "master" }
  s.source_files  = "RNCloudPayments/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  