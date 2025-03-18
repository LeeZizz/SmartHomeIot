const cards = document.querySelectorAll('.card');
console.log("Cards found:", cards.length); // Kiểm tra số thẻ card

cards.forEach((card) => {
    const p = card.querySelector('p');
    let value = parseFloat(p.textContent.replace(/[^\d.-]/g, '').trim()); // Lấy số bất kể ký tự
    value = parseInt(value); // Chuyển đổi thành số nguyên
    const title = card.querySelector('h3').textContent.toLowerCase(); // Lấy tên chỉ số

    console.log("Title:", title, "Value:", value); // Kiểm tra giá trị

    // Kiểm tra nếu value là NaN
    if (isNaN(value)) {
        console.error("Giá trị không hợp lệ:", p.textContent);
        return; // Nếu giá trị không hợp lệ, thoát khỏi vòng lặp
    }

    // Thêm class tương ứng cho thẻ card
    if (title.includes('temperature')) {
        card.classList.add('temp');
        if (value <= 10) {
            card.classList.add('temp-low');
        } else if (value <= 28) {
            card.classList.add('temp-medium');
        } else if (value <= 35) {
            card.classList.add('temp-high');
        } else {
            card.classList.add('temp-danger'); // Trên 30°C
        }
    } else if (title.includes('humidity')) {
        card.classList.add('hum');
        if (value <= 30) {
            card.classList.add('hum-low');
        } else if (value <= 60) {
            card.classList.add('hum-medium');
        } else {
            card.classList.add('hum-high'); // Trên 60%
        }
    } else if (title.includes('light intensity')) {
        card.classList.add('light');
        if (value <= 30) {
            card.classList.add('light-low');
        } else if (value <= 60) {
            card.classList.add('light-medium');
        } else {
            card.classList.add('light-high'); // Trên 60%
        }
    }
});








function toggleDevice(button, device) {
    button.classList.toggle('off');

    const icon = button.parentElement.querySelector('.device-icon');

    if (button.classList.contains('off')) {
        button.textContent = 'Off';
        icon.classList.remove('active');

        // Tắt hoạt ảnh tương ứng
        if (device === 'lamp') {
            icon.classList.remove('lamp-on');
        } else if (device === 'fan') {
            icon.classList.remove('fan-rotate');
        } else if (device === 'system') {
            icon.classList.remove('system-rotate');
        } else if (device === 'tv') {
            icon.classList.remove('tv-on');
        }
    } else {
        button.textContent = 'On';
        icon.classList.add('active');

        // Bật hoạt ảnh tương ứng
        if (device === 'lamp') {
            icon.classList.add('lamp-on');
        } else if (device === 'fan') {
            icon.classList.add('fan-rotate');
        } else if (device === 'system') {
            icon.classList.add('system-rotate');
        } else if (device === 'tv') {
            icon.classList.add('tv-on');
        }
    }
}





// JavaScript to handle modal functionality
var modal = document.getElementById("userModal");
var profile = document.getElementById("profileAvatar");
var span = document.getElementById("closeModal");

profile.onclick = function () {
    modal.style.display = "block";
}

span.onclick = function () {
    modal.style.display = "none";
}

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function toggleDevice(button, device) {
    button.classList.toggle('off');

    const icon = button.parentElement.querySelector('.device-icon');

    if (button.classList.contains('off')) {
        button.textContent = 'Off';
        icon.classList.remove('active');

        // Tắt hoạt ảnh tương ứng
        if (device === 'lamp') {
            icon.classList.remove('lamp-on');
        } else if (device === 'fan') {
            icon.classList.remove('fan-rotate');
        } else if (device === 'system') {
            icon.classList.remove('system-rotate');
        } else if (device === 'tv') {
            icon.classList.remove('tv-on');
        }
    } else {
        button.textContent = 'On';
        icon.classList.add('active');

        // Bật hoạt ảnh tương ứng
        if (device === 'lamp') {
            icon.classList.add('lamp-on');
        } else if (device === 'fan') {
            icon.classList.add('fan-rotate');
        } else if (device === 'system') {
            icon.classList.add('system-rotate');
        } else if (device === 'tv') {
            icon.classList.add('tv-on');
        }
    }
}



document.getElementById("toggleBtn").addEventListener("click", function () {
    var sidebar = document.getElementById("sidebar");
    sidebar.classList.toggle("collapsed");
});

function toggleSidebar() {
    var sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('collapsed');
}











