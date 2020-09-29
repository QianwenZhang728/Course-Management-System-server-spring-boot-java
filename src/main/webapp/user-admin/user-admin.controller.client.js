(function () {
    let $usernameFld, $passwordFld;
    let $removeBtn, $editBtn, $createBtn, $updateBtn;
    let $firstNameFld, $lastNameFld, $roleFld;
    let $userRowTemplate, $tbody;
    let userService = new AdminUserServiceClient();
    let users, clone, selectedUserIndex


    $(main);
    function main() {
        $tbody = $("tbody")
        $userRowTemplate = $("tr.wbdv-template")

        $usernameFld = $("#usernameFld")
        $firstNameFld = $("#firstNameFld")
        $lastNameFld = $("#lastNameFld")
        $roleFld = $("#roleFld")

        $createBtn = $(".wbdv-create").click(createUser)
        $updateBtn = $(".wbdv-update").click(updateUser)

        findAllUsers()
    }

    // function deleteUser1(event) {
    //     const deleteBtn = $(event.currentTarget)
    //     deleteBtn.parents("tr.wbdv-template").remove()
    //     console.log(deleteBtn)
    // }

    function createUser() {
        console.log("create")

        const newUser = {
            username: $usernameFld.val(),
            first: $firstNameFld.val(),
            last: $lastNameFld.val(),
            role: $roleFld.val()
        }

        userService.createUser(newUser)
            .then(response => findAllUsers())
    }

    function findAllUsers() {
        userService.findAllUsers()
            .then(function(_users) {
                console.log(_users)
                users = _users
                renderUsers(users)
            })
    }

    function findUserById(userId) {

    }

    function deleteUser(_index) {
        const user = users[_index]
        const userId = user._id

        userService.deleteUser(userId)
            .then(response => findAllUsers())
    }

    function selectUser(index) {
        selectedUserIndex = index
        $("#usernameFld").val(users[index].username)
        $("#firstNameFld").val(users[index].first)
        $("#lastNameFld").val(users[index].last)
        $("#roleFld").val(users[index].role)

    }

    function updateUser() {
        const newUserName = $("#usernameFld").val()
        const newFirstName = $("#firstNameFld").val()
        const newLastName = $("#lastNameFld").val()
        const newRole = $("#roleFld").val()
        const userId = users[selectedUserIndex]._id

        userService.updateUser(userId, {
            username: newUserName,
            first: newFirstName,
            last: newLastName,
            role: newRole
        })
            .then(response => findAllUsers())
    }

    function renderUser(user) { }

    function renderUsers(users) {
        $tbody.empty()

        for(let i = 0; i < users.length; i ++) {
            const user = users[i]

            clone = $userRowTemplate.clone()

            clone.find(".wbdv-username").text(user.username)
            clone.find(".wbdv-first-name").text(user.first)
            clone.find(".wbdv-last-name").text(user.last)
            clone.find(".wbdv-role").text(user.role)
            clone.find(".wbdv-remove").click(() => deleteUser(i))
            clone.find(".wbdv-edit").click(() => selectUser(i))

            $tbody.append(clone)
        }
    }
})();