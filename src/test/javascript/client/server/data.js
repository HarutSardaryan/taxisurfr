module.exports = {
    people: getRoutes()
};

function getRoutes() {
    return [
        {id: 1, routeStart: 'John', routeEnd: 'Papa', age: 25, location: 'Florida'},
        {id: 2, routeStart: 'Ward', routeEnd: 'Bell', age: 31, location: 'California'},
        {id: 3, routeStart: 'Colleen', routeEnd: 'Jones', age: 21, location: 'New York'},
        {id: 4, routeStart: 'Madelyn', routeEnd: 'Green', age: 18, location: 'North Dakota'},
        {id: 5, routeStart: 'Ella', routeEnd: 'Jobs', age: 18, location: 'South Dakota'},
        {id: 6, routeStart: 'Landon', routeEnd: 'Gates', age: 11, location: 'South Carolina'},
        {id: 7, routeStart: 'Haley', routeEnd: 'Guthrie', age: 35, location: 'Wyoming'},
        {id: 8, routeStart: 'Aaron', routeEnd: 'Jinglehiemer', age: 22, location: 'Utah'}
    ];
}
