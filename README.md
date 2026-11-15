Trần Hoàng Đức Linh
Mssv: 202418932


# Smart Home Mini

Ứng dụng điều khiển nhà thông minh gồm đèn, quạt và cửa (Java).

## Vị trí sử dụng từng pattern

| Pattern | Vị trí | Vai trò |
|---|---|---|
| Factory | `smarthome/pattern/factory/DeviceFactory.java` | Tạo `SmartLight`, `SmartFan`, `SmartDoor` theo chuỗi loại, trả về `IDevice` |
| Singleton | `smarthome/pattern/singleton/HomeConfiguration.java` | Một cấu hình chung duy nhất (`homeName`, `nightLightLevel`) |
| Facade | `smarthome/pattern/facade/SmartHomeFacade.java` | `goToWork()` và `goToSleep()` gom nhiều thao tác thiết bị |

## Lợi ích đạt được

- **Factory:** client chỉ làm việc với `IDevice`, không gọi constructor cụ thể; thêm loại thiết bị mới chỉ sửa ở factory.
- **Singleton:** mọi nơi dùng chung một cấu hình, không bị lệch dữ liệu; khởi tạo muộn và an toàn luồng.
- **Facade:** bật chế độ Đi làm / Đi ngủ bằng một lệnh, client không cần biết chi tiết từng thiết bị.

## Một hạn chế của thiết kế

`SmartHomeFacade` gọi trực tiếp `HomeConfiguration.getInstance()` nên phụ thuộc ngầm vào Singleton, khó thay cấu hình giả khi unit test.

## Phần G: Câu hỏi trả lời

**1. Factory đang che giấu quyết định thiết kế nào?**
Factory che giấu quyết định tạo lớp cụ thể nào (`SmartLight`, `SmartFan` hay `SmartDoor`) và khởi tạo ra sao. Client chỉ đưa chuỗi loại và tên, rồi nhận về `IDevice`.

**2. Singleton bảo đảm điều gì, nhưng tạo ra rủi ro nào?**
Singleton bảo đảm toàn ứng dụng chỉ có một thể hiện của `HomeConfiguration` và có một điểm truy cập chung. Rủi ro: tạo phụ thuộc ẩn và trạng thái toàn cục, các test ảnh hưởng lẫn nhau, khó thay bằng đối tượng giả, và dễ bị lạm dụng như biến global.

**3. Facade khác Factory ở mục đích chính như thế nào?**
Factory giải quyết việc *tạo* đối tượng, còn Facade giải quyết việc *đơn giản hóa cách dùng* một nhóm đối tượng đã có, bằng cách gom nhiều thao tác thành một lệnh.

**4. Nếu bỏ `SmartHomeFacade`, client sẽ phải biết thêm những chi tiết nào?**
Client phải tự biết có những thiết bị nào, thiết bị nào khóa được (phải ép sang `ILockable`), thứ tự thao tác của từng chế độ (Đi làm, Đi ngủ), và tự đọc `nightLightLevel` từ `HomeConfiguration` để thông báo mức sáng.


