# Chương 02 - Thiết lập (Setup)

## 1. Cấu phẫu của một ứng dụng Android (Android App's Anatomy)
* **Ngôn ngữ lập trình**: Các ứng dụng Android thường được viết bằng ngôn ngữ Java.
* **Thư viện**: Ứng dụng phải nhập (import) các thư viện Android (như `android.jar`, `maps.jar`) để có các chức năng cần thiết chạy trên hệ điều hành.
* **Thành phần cốt lõi**: Bao gồm các lớp do người dùng định nghĩa, thư viện bên thứ ba, tệp XML (định nghĩa giao diện/view), tài nguyên đa phương tiện, dữ liệu (tệp tin, chuỗi) và tệp **Manifest**.
* **Tệp Manifest**: Tóm tắt cấu trúc và các quyền hạn (permissions) mà ứng dụng yêu cầu.
* **Biên dịch**: Các thành phần được biên dịch thành một gói Android duy nhất có thể triển khai gọi là tệp **.apk**.
* **Môi trường thực thi**:
    * **Dalvik Virtual Machine (DVM)**: Môi trường chạy JIT (Just-in-Time), thông dịch mã byte-code khi cần thiết.
    * **Android Runtime (ART)**: Môi trường chạy AOT (Ahead-of-Time), biên dịch mã trước khi thực thi. ART giúp cải thiện hiệu suất, tiết kiệm pin và gỡ lỗi tốt hơn.

## 2. Công cụ xây dựng ứng dụng (Tools for Constructing Android Apps)
* **IDE**: Do ứng dụng Android có cấu trúc phức tạp, việc sử dụng Môi trường Phát triển Tích hợp (IDE) là bắt buộc.
* **Android Studio**: Đây là IDE chính thức và được ưu tiên nhất hiện nay, được xây dựng dựa trên IntelliJ IDEA.

## 3. Thiết bị ảo và Ảnh đĩa (AVD - Emulator & Disk Images)
* **AVD Manager**: Công cụ dùng để tạo, sửa và quản lý các thiết bị Android ảo (AVD).
* **Disk Images**: Khi tạo AVD, hệ thống sẽ tạo ra các file ảnh đĩa như: nhân OS, hệ thống Android, dữ liệu người dùng (`userdata-qemu.img`) và thẻ nhớ SD giả lập (`sdcard.img`).
* **Truyền file**: Có thể dùng 'Device File Explorer' trong Android Studio để chuyển dữ liệu giữa máy tính và thẻ nhớ của máy ảo.

## 4. Dự án Hello World trong Android Studio
* **Khởi tạo**: Sử dụng wizard để đặt tên ứng dụng (Application Name) và tên gói (Package Name).
* **Cấu trúc thư mục**:
    * `java/`: Chứa mã nguồn Java của các Activity.
    * `res/`: Chứa tài nguyên như hình ảnh (drawable), giao diện (layout XML), chuỗi ký tự (strings) và menu.
    * `Manifests`: Chứa tệp `AndroidManifest.xml`.

## 5. Trình giả lập Android - Tìm hiểu sâu hơn (Looking Under the Hood)
* **ADB (Android Debug Bridge)**: Công cụ dòng lệnh cho phép tương tác trực tiếp với các phần sâu bên trong hệ điều hành (`adb shell`).
* **Xác định thiết bị**: Lệnh `adb devices` liệt kê tất cả thiết bị và máy ảo đang kết nối.
* **Lệnh Linux**: Android hỗ trợ các lệnh Linux cơ bản như `ls`, `mkdir`, `rm`, `mv`, `cat`, `cd`, `pwd`, `chmod`. (Lưu ý: Không có lệnh `cp` trực tiếp, thường dùng `cat` để thay thế).
* **Cài đặt qua ADB**:
    * Lấy tệp từ máy: `adb pull <đường_dẫn_điện_thoại> <đường_dẫn_máy_tính>`.
    * Cài đặt ứng dụng: `adb install <đường_dẫn_file_apk>`.

## 6. Điều khiển máy ảo nâng cao (Advanced Emulator Controls)
* **Tương tác qua Telnet**: Có thể kết nối qua cổng 5554 để mô phỏng các sự kiện:
    * Gửi tin nhắn: `sms send <số_điện_thoại> <nội_dung>`.
    * Gọi điện: `gsm call <số_điện_thoại>`.
* **Extended Controls (Giao diện đồ họa)**:
    * **Telephony**: Giả lập trạng thái mạng, tốc độ kết nối (GPRS, 3G, EDGE) và chuyển vùng (roaming).
    * **Location**: Gửi tọa độ GPS giả lập thủ công hoặc thông qua các tệp lộ trình như GPX hoặc KML.