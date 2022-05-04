using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiHeroku.Migrations
{
    public partial class create9 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "CreatedDate",
                table: "UserInfosExample");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<DateTime>(
                name: "CreatedDate",
                table: "UserInfosExample",
                type: "timestamp with time zone",
                nullable: true);
        }
    }
}
