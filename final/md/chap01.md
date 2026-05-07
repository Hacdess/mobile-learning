# Cơ bản về Android

## 1. Sự Tiến Hóa Của Điện Thoại Di Động (Mobile Phone Evolution)
* **Các cột mốc lịch sử**:
    * Năm 1876: Alexander Graham Bell là người đầu tiên nhận bằng sáng chế cho điện thoại điện.
    * Năm 1936: Alfred Gross phát minh và nhận bằng sáng chế cho Walkie-talkie, CB radio, và máy nhắn tin điện thoại (Telephone Pager).
    * Năm 1975: Tiến sĩ Martin Cooper phát minh ra điện thoại vô tuyến cầm tay thương mại đầu tiên của Motorola.
    * Năm 2007: iPhone và nền tảng Android chính thức xuất hiện.
* **Bản chất của điện thoại thông minh**:
    * Một chiếc điện thoại thông minh (Smart cellular phone) thực chất là sự kết hợp giữa radio và máy tính.
    * Ngành công nghiệp này là sự hội tụ của phần mềm, viễn thông, chất bán dẫn và tiếp thị.
* **Nguyên lý viễn thông di động (Mạng tế bào)**:
    * Ý tưởng cốt lõi của truyền thông di động là chia một thành phố lớn thành các khu vực nhỏ gọi là các "ô" (cells), mỗi ô chứa một Trạm gốc (Base-Station).
    * Mỗi ô có hình lục giác và bao phủ một diện tích xấp xỉ 10 dặm vuông (khoảng 26 km²).
    * Các Trạm gốc hoạt động với công suất vừa đủ để chỉ tiếp cận những người dùng bên trong ô của nó.
    * Nhờ sử dụng các máy phát công suất thấp, các dải tần số có thể được tái sử dụng ở các ô không liền kề nhau.

## 2. Tổng Quan Về Nền Tảng Android (Software: What is Android?)
* **Định nghĩa**: Android OS là một hệ điều hành mã nguồn mở dựa trên nền tảng Linux, được thiết kế cho các thiết bị di động.
* **Nhà phát triển**: Hệ điều hành này được phát triển bởi Open Handset Alliance (OHA) và Google Inc.
    * Open Handset Alliance là một liên minh gồm hơn 80 công ty kinh doanh di động và công nghệ.
* **Hệ sinh thái và Ứng dụng**:
    * Android có sẵn một số ứng dụng gốc để hỗ trợ gọi điện thoại, nhắn tin, v.v..
    * Các nhà phát triển Java bên thứ ba có thể sử dụng Android API để mở rộng chức năng của thiết bị.
    * Google cung cấp một chợ điện tử trực tuyến để các nhà phát triển bên thứ ba có thể bán và phân phối ứng dụng của họ.
* **Các thành phần phần cứng và phần mềm của Android**:
    * Máy ảo Dalvik (sắp được thay thế bởi ART).
    * Trình duyệt tích hợp sẵn (dựa trên WebKit).
    * Khả năng đồ họa (hỗ trợ tăng tốc phần cứng).
    * Sử dụng SQLite để lưu trữ dữ liệu có cấu trúc.
    * Hỗ trợ đa phương tiện (âm thanh/video).
    * Hỗ trợ các công nghệ viễn thông và kết nối như GSM, Bluetooth, EDGE, 3G, 4G, NFC, và Wi-Fi (phụ thuộc vào nhà sản xuất phần cứng).
    * Hỗ trợ đa dạng cảm biến: Camera, GPS, la bàn, gia tốc kế, con quay hồi chuyển, cảm biến khoảng cách/ánh sáng, áp suất, quét vân tay, nhịp tim.
    * Công cụ phát triển phần mềm và Framework ứng dụng (Android Studio, thiết bị giả lập, công cụ gỡ lỗi, v.v.).

## 3. Intents Trong Android (Android Intents)
* **Khái niệm**: Intent là một yêu cầu đối với các dịch vụ được cung cấp bởi thiết bị chạy Android.
* **Thành phần của một Intent**:
    * Hành động hoặc dịch vụ mong muốn (desired action or service).
    * Dữ liệu (data).
    * Danh mục (category) của thành phần sẽ xử lý Intent và các hướng dẫn về cách khởi chạy một Activity mục tiêu.
* **Hành động và Dữ liệu (Action & Data)**:
    * *Action*: Là hành động chung cần thực hiện, ví dụ như `ACTION_VIEW`, `ACTION_EDIT`, `ACTION_MAIN`, v.v..
    * *Data*: Là dữ liệu để thao tác (ví dụ: một bản ghi thông tin người trong cơ sở dữ liệu danh bạ), thường được biểu diễn dưới dạng một Uri.
* **Một số ví dụ về cặp Action/Data của Intent**:
    * `ACTION_VIEW content://contacts/1`: Hiển thị thông tin về người có định danh là "1".
    * `ACTION_DIAL content://contacts/1`: Hiển thị màn hình gọi điện với thông tin người dùng được điền sẵn.
    * `ACTION_VIEW tel:123`: Hiển thị màn hình gọi điện với số "123" được điền sẵn.
    * `ACTION_VIEW content://contacts/`: Hiển thị danh sách mọi người để người dùng có thể lướt xem.
* **Ví dụ Code**:
    * Để gọi một Intent với nhiệm vụ mở danh bạ liên lạc có trong điện thoại, ta dùng đoạn mã: `Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people")); startActivity(myIntent);`.

## 4. Cấu Phẫu Một Ứng Dụng Android (Dissecting an Android Application)
* **Tệp cấu hình AndroidManifest.xml**:
    * Mỗi ứng dụng Android đều bắt buộc phải có một tệp `AndroidManifest.xml` nằm trong thư mục gốc của nó.
    * Tệp manifest cung cấp các thông tin thiết yếu về ứng dụng cho hệ thống Android.
    * Tệp này chứa các mục khai báo cho từng activity, các yêu cầu sử dụng thư viện, và các quyền hạn (permissions) đặc biệt cần thiết để xây dựng ứng dụng.
* **Các phần tử XML (XML-elements) thường dùng trong tệp Manifest**:
    * Bao gồm các thẻ như: `<application>`, `<activity>`, `<intent-filter>`, `<action>`, `<category>`, `<service>`, `<receiver>`, `<provider>`, `<uses-permission>`, `<uses-library>`, `<uses-sdk>`, v.v..
* **Ví dụ thực tế - Ứng dụng chuyển đổi tiền tệ**:
    * **Logic (Java code)**: Sử dụng các điều khiển giao diện như `Button`, `EditText` và liên kết với lớp hoạt động (`Activity`). Gán bộ lắng nghe sự kiện click (`OnClickListener`) cho các nút bấm (ví dụ như nút "Convert" để thực hiện tính toán tỷ giá và nút "Clear" để làm sạch dữ liệu).
    * **Giao diện (XML Layout)**: Sử dụng cấu trúc `LinearLayout` làm nền tảng (sắp xếp theo "vertical" hoặc "horizontal"), kết hợp với các widget như `TextView` (nhãn hiển thị) và `EditText` (ô nhập liệu).. Những ô chỉ dành để hiển thị kết quả có thể được vô hiệu hóa nhập liệu bằng thuộc tính như `android:editable="false"` hoặc `inputType=TYPE_NULL`.
