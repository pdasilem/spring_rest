$(document).ready(); {
    getTableUser()
}
function getTableUser() {
    fetch('/user').then(
        response => {
            response.json().then(
                data => {
                    let str = '<tr>' +
                        '<td>' + data.id + '</td>' +
                        '<td>' + data.name + '</td>' +
                        '<td>' + data.lastName + '</td>' +
                        '<td>' + data.age + '</td>' +
                        '<td>' + data.email + '</td>' +
                        '<td>' + data.roles[0] + '</td>' +
                        '</tr>';
                    $('#userTable tbody').empty().append(str);
                });
        });
}