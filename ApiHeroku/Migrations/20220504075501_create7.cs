using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiHeroku.Migrations
{
    public partial class create7 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<DateOnly>(
                name: "booking_date",
                table: "Bookings",
                type: "date",
                nullable: false,
                defaultValue: new DateOnly(1, 1, 1));

            migrationBuilder.AddColumn<DateOnly>(
                name: "from_date",
                table: "Bookings",
                type: "date",
                nullable: false,
                defaultValue: new DateOnly(1, 1, 1));

            migrationBuilder.AddColumn<DateOnly>(
                name: "to_date",
                table: "Bookings",
                type: "date",
                nullable: false,
                defaultValue: new DateOnly(1, 1, 1));
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "booking_date",
                table: "Bookings");

            migrationBuilder.DropColumn(
                name: "from_date",
                table: "Bookings");

            migrationBuilder.DropColumn(
                name: "to_date",
                table: "Bookings");
        }
    }
}
