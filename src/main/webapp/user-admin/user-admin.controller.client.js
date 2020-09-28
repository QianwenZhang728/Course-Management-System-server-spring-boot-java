(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    let users
    let tbody
    let template
    let clone

    $(main);
    function main() {
        tbody = $("tbody")
        template = $("tr.wbdv-template")
        $createBtn = $(".wbdv-create").click(createUser)
        $firstNameFld = $("#firstNameFld")
        $usernameFld = $("#usernameFld")
        $(".wbdv-update").click(updateUser)
        $(".wbdv-create").click(createUser)

        userService.findAllUsers()
            .then(function(_users) {
                console.log(_users)
                users = _users
                renderUsers(users)
            })

    }

    function createUser() {
        const username = $usernameFld.val()
        const firstName = $firstNameFld.val()

        $usernameFld.val("")
        $firstNameFld.val("")

        const newUser = {
            username: username,
            first: firstName,
            last: 'TBD1',
            role: 'TBD2'
        }

        userService.createUser(newUser)
            .then(actualNewUser => {
                users.push(actualNewUser)
                renderUsers(users)
            })
    }

    function findAllUsers() { }
    function findUserById() { }

    function deleteUser(_index) {
        const user = users[_index]
        const userId = user._id

        userService.deleteUser(userId)
            .then(response => {
                users.splice(_index, 1)
                renderUsers(users)
            })
    }

    let selectedUserIndex = -1
    function selectUser(index) {
        selectedUserIndex = index
        $("#usernameFld").val(users[index].username)
        $("#firstnameFld").val(users[index].first)
        $("#lastnameFld").val(users[index].last)

    }

    function updateUser() {
        const newUserName = $("#usernameFld").val()
        const newFirstName = $("#firstnameFld").val()
        const newLastName = $("#lastnameFld").val()
        const userId = users[selectedUserIndex]._id

        userService.updateUser(userId, {
            username: newUserName,
            first: newFirstName
        })
            .then(response => {
                users[selectedUserIndex].username = newUserName
                users[selectedUserIndex].first = newFirstName
                renderUsers(users)
        })


    }

    function renderUser(user) { }

    function renderUsers(users) {
        tbody.empty()

        for(let i = 0; i < users.length; i ++) {
            const user = users[i]

            clone = template.clone()

            clone.find(".wbdv-username").text(user.username)
            clone.find(".wbdv-first-name").text(user.first)
            clone.find(".wbdv-last-name").text(user.last)
            clone.find(".wbdv-remove").click(() => deleteUser(i))
            clone.find(".wbdv-edit").click(() => selectUser(i))

            tbody.append(clone)
        }
    }
})();