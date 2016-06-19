angular.module("myApp", []).controller("myCtrl", function($scope, $http) {
	$scope.searchBlog = function() {
		$.ajax({
			method : 'POST',
			url : 'blog/getBlog',
			data : {
				info : $scope.info
			},
		}).success(function(data) {
			console.log(data)
			$scope.blogs = data.results;
		})
	};
	$http({
		url : 'blog/blogs?pageNum=1&pageSize=5',
		method : 'GET'
	}).success(function(data) {
		console.log(data)
		$scope.blogs = data.page.results;
		getPage(data.page, $scope, $http);
	}).error(function(data) {
		alert(data.msg)
	});
});